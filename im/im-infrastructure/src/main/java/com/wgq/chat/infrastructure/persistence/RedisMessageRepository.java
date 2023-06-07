package com.wgq.chat.infrastructure.persistence;

import com.sheep.core.spi.JsonFactory;
import com.sheep.json.Json;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.constant.Extension;
import com.sheep.protocol.constant.SheepError;
import com.sheep.support.PlaceHolderParser;
import com.sheep.support.PropertyAccessor;
import com.sheep.utils.ConfigUtils;
import com.sheep.utils.FileUtils;
import com.wgq.chat.domain.netty.Protocol;
import com.wgq.chat.infrastructure.commons.ConfigKey;
import com.wgq.chat.infrastructure.commons.PropertyAccessBuilder;
import com.wgq.chat.infrastructure.commons.RedisKey;
import com.wgq.chat.infrastructure.converter.MessageConverter;
import com.wgq.chat.protocol.MessageKey;
import com.wgq.chat.protocol.dto.MessageDTO;
import com.wgq.chat.protocol.param.MessageCancelParam;
import com.wgq.chat.protocol.param.MessageReadParam;
import com.wgq.chat.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.wgq.chat.protocol.constant.Chat.*;

@Named
public class RedisMessageRepository implements MessageRepository {
    private static Logger logger = LoggerFactory.getLogger(RedisMessageRepository.class);

    @Inject
    private RedisTemplate redisTemplate;
    @Inject
    private MessageConverter messageConverter;

    private Json json = JsonFactory.getProvider();

    private String generateImageId(Integer userId) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String rootPhysicalPath = ConfigUtils.getValue(ConfigKey.IMAGE_PHYSICAL_ROOT_PATH);
        String physicalUrl = rootPhysicalPath +
                File.separator +
                year + File.separator +
                month + File.separator +
                day + File.separator +
                userId + File.separator +
                calendar.getTimeInMillis() + Extension.JPG;
        return physicalUrl;
    }

    @Override
    public void cancel(MessageCancelParam messageCancel) throws BusinessException {
        PropertyAccessor propertyAccessor = PropertyAccessBuilder.buildBySessionKey(messageCancel.getSessionKey());
        String redisKey = PlaceHolderParser.parse(RedisKey.MESSAGE_KEY, propertyAccessor);
        String msgKey = new MessageKey(messageCancel.getSender(), messageCancel.getClientSendTime()).key();
        String msg = (String) redisTemplate.opsForHash().get(redisKey, msgKey);
        MessageDTO message = this.json.parse(msg, MessageDTO.class);
        if (message == null) {
            throw new BusinessException(SheepError.GLOBAL_REQUEST_ID_NOT_EXIST);
        }
        if (message.getSender() != messageCancel.getSender()) {
            throw new BusinessException(SheepError.GLOBAL_PARAMETER_IS_ILLEGAL);
        }
        redisTemplate.opsForHash().delete(redisKey, msgKey);
        redisTemplate.opsForList().remove("l" + redisKey, 1, msgKey);
    }

    @Override
    public String saveImageContent(Protocol protocol) {
        if (IMAGE_MESSAGE != protocol.getMessageType()) {
            return null;
        }
        String physicalUrl = this.generateImageId(protocol.getSender());
        FileUtils.getInstance().generateImage(protocol.getContent(), physicalUrl);
        String rootPhysicalPath = ConfigUtils.getValue(ConfigKey.IMAGE_PHYSICAL_ROOT_PATH);
        String rootWebPath = ConfigUtils.getValue(ConfigKey.IMAGE_WEB_ROOT_PATH);

        String webUrl = physicalUrl.replace(rootPhysicalPath, rootWebPath);
        //转换成 msg id
        protocol.setContent(webUrl);
        return webUrl;
    }

    public List<MessageDTO> getMessages(String messageKey) {
        Map<String, String> messages = redisTemplate.opsForHash().entries(messageKey);
        List<MessageDTO> messageList = new ArrayList<>(messages.size());
        for (String key : messages.keySet()) {
            String message = messages.get(key);
            try {
                messageList.add(this.json.parse(message, MessageDTO.class));
            } catch (Exception e) {
                logger.error("parse json error key:{},message:{}", key, message, e);
            }
        }
        messageList.sort(Comparator.comparing(MessageDTO::getServerTime));
        return messageList;
    }

    @Override
    public void saveMessage(Protocol protocol) {
        this.saveImageContent(protocol);
        MessageDTO message = this.messageConverter.assembleMessage(protocol);
        PropertyAccessor propertyAccessor = PropertyAccessBuilder.buildBySessionKey(protocol.getChatSession().getSessionKey());
        String messageKey = PlaceHolderParser.parse(RedisKey.MESSAGE_KEY, propertyAccessor);
        String lkey = "l" + messageKey;
        redisTemplate.opsForList().rightPush(lkey, message.getKey());
        redisTemplate.opsForHash().put(messageKey, message.getKey(), this.json.toString(message));
        if (redisTemplate.opsForHash().size(messageKey) > MAX_MSG_OF_SESSION) {
            String firstKey = (String) redisTemplate.opsForList().leftPop(lkey);
            redisTemplate.opsForHash().delete(messageKey, firstKey);
        }
        redisTemplate.expire(messageKey, MESSAGE_EXPIRE_DAYS, TimeUnit.DAYS);
    }

    @Override
    public void read(MessageReadParam messageRead) {
        PropertyAccessor propertyAccessor = PropertyAccessBuilder.buildBySessionAndUserId(messageRead.getSessionKey(), messageRead.getUserId());
        String sessionReadKey = PlaceHolderParser.parse(RedisKey.USER_SESSION_READ, propertyAccessor);
        redisTemplate.opsForValue().set(sessionReadKey, System.currentTimeMillis() + "");
    }

    @Override
    public Map<String, Long> getLastRead(Integer me, List<String> sessionKeys) {
        List<String> messageReadKeys = new ArrayList<>(sessionKeys.size());
        for (String sessionKey : sessionKeys) {
            PropertyAccessor propertyAccessor = PropertyAccessBuilder.buildBySessionAndUserId(sessionKey, me);
            String messageReadKey = PlaceHolderParser.parse(RedisKey.USER_SESSION_READ, propertyAccessor);
            messageReadKeys.add(messageReadKey);
        }
        List<String> lastReadTimes = redisTemplate.opsForValue().multiGet(messageReadKeys);
        Map<String, Long> lastReadTimeMap = new HashMap<>(sessionKeys.size());
        for (int i = 0; i < sessionKeys.size(); i++) {
            String lastReadTime = lastReadTimes.get(i);
            if (lastReadTime != null) {
                lastReadTimeMap.put(sessionKeys.get(i), Long.parseLong(lastReadTime));
            }
        }
        return lastReadTimeMap;
    }

    @Override
    public List<MessageDTO> getMessageBySession(String session) {
        PropertyAccessor propertyAccessor = PropertyAccessBuilder.buildBySessionKey(session);
        String messageKey = PlaceHolderParser.parse(RedisKey.MESSAGE_KEY, propertyAccessor);
        return this.getMessages(messageKey);
    }
}
