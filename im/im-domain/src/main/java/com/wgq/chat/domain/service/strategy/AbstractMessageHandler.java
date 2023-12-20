package com.wgq.chat.domain.service.strategy;

import cn.hutool.core.bean.BeanUtil;
import com.sheep.protocol.BusinessException;
import com.wgq.chat.assemble.MessageAssemble;
import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.protocol.enums.MessageTypeEnum;
import com.wgq.chat.protocol.param.MessageSendParam;
import com.wgq.chat.repository.MessageRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;

/**
 * @ClassName: AbstractMsgHandler
 * @Author : wgq
 * @Date :2023/9/14  17:06
 * @Description:
 * @Version :1.0
 */
public abstract class AbstractMessageHandler<T> {

    @Inject
    private MessageAssemble messageAssemble;

    @Inject
    private MessageRepository messageRepository;

    private Class<T> bodyClass;

    @PostConstruct
    private void init() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.bodyClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
        MessageHandlerFactory.register(getMessageTypeEnum().getType(), this);
    }

    /**
     * 消息类型
     */
    abstract MessageTypeEnum getMessageTypeEnum();

    /**
     * 校验消息——保存前校验
     */
    protected void checkMessage(T body,Long roomId, Long uid) throws BusinessException{

    }

    @Transactional
    public Long checkAndSaveMessage(MessageSendParam messageSendParam, Long userId) throws BusinessException {
        T body =  this.toBean(messageSendParam.getBody());
        //统一校验
//        Asserts.allCheckValidateThrow(body);
        // 子类扩展校验
        checkMessage(body,messageSendParam.getRoomId(),userId);
        MessageBO messageBO = this.messageAssemble.assembleMessageBO(messageSendParam,userId);
        //统一保存
        Long messageId = this.messageRepository.save(messageBO);
        //子类扩展保存
        messageBO.setId(messageId);
        saveMessage(messageBO,body);
        return messageId;
    }

    protected T toBean(Object body) throws BusinessException {
        if (bodyClass.isAssignableFrom(body.getClass())){
            return (T) body;
        }
        //TODO 后期替换hutool工具类
        return BeanUtil.toBean(body, bodyClass);
    }


    /**
     * 保存消息
     */
    protected abstract void saveMessage(MessageBO messageBO, T body) throws BusinessException;

    /**
     * 展示消息
     */
    public abstract Object showMessage(MessageBO message);

    /**
     * 被回复时——展示的消息
     */
    public abstract Object showReplyMessage(MessageBO message);

    /**
     * 会话列表——展示的消息
     */
    public abstract String showContactMessage(MessageBO message);


}
