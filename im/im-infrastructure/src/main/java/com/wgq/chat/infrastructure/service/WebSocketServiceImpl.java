package com.wgq.chat.infrastructure.service;

import com.sheep.core.spi.JsonFactory;
import com.sheep.json.Json;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.LoginUser;
import com.sheep.utils.CollectionsUtils;
import com.wgq.chat.domain.netty.NettyUtil;
import com.wgq.chat.domain.netty.UserContainer;
import com.wgq.chat.domain.service.WebSocketService;
import com.wgq.chat.mq.ImMQPublisher;
import com.wgq.chat.protocol.constant.MQConstant;
import com.wgq.chat.protocol.dto.AuthorizeDTO;
import com.wgq.chat.protocol.dto.ChannelExtraDTO;
import com.wgq.chat.protocol.dto.PushBashDTO;
import com.wgq.chat.protocol.enums.PushTypeEnum;
import com.wgq.chat.protocol.enums.WebsocketResponseTypeEnum;
import com.wgq.chat.protocol.event.UserOfflineEvent;
import com.wgq.chat.protocol.event.UserOnlineEvent;
import com.wgq.chat.protocol.event.UserProfileEvent;
import com.wgq.passport.api.UserProfileAppService;
import com.wgq.passport.api.UserSecurityService;
import com.wgq.passport.protocol.dto.LoginDTO;
import com.wgq.passport.protocol.dto.UserProfileDTO;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName: WebSocketServiceImpl
 * @Author : wgq
 * @Date :2023/8/22  15:35
 * @Description:
 * @Version :1.0
 */
@Named
public class WebSocketServiceImpl implements WebSocketService {

    private Logger logger = LoggerFactory.getLogger(WebSocketServiceImpl.class);

    private Json json = JsonFactory.getProvider();

    private UserContainer container = UserContainer.getContainer();


    @Inject
    @Qualifier("websocketExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Inject
    private UserProfileAppService userProfileAppService;

    @Inject
    private UserSecurityService userSecurityService;

    @Inject
    private ImMQPublisher imMQPublisher;

    @Override
    public void connect(Channel channel) {
        container.getOnlineMap().put(channel, new ChannelExtraDTO());
    }

    @Override
    public void removed(Channel channel) {
        ChannelExtraDTO channelExtraDTO = container.getOnlineMap().get(channel);
        Optional<Long> uidOptional = Optional.ofNullable(channelExtraDTO)
                .map(ChannelExtraDTO::getUserId);
        boolean offlineAll = container.offline(channel, uidOptional);
        if (uidOptional.isPresent() && offlineAll) {//已登录用户断连,并且全下线成功
            //发送给对应的用户
//            UserProfileDTO userProfileDTO = new UserProfileDTO();
//            userProfileDTO.setUserId(uidOptional.get());
//            userProfileDTO.setGmtModified(System.currentTimeMillis());
//            this.imMQPublisher.publish(MQConstant.USER_OFFLINE_TOPIC,new PushBashDTO<UserProfileDTO>(WebsocketResponseTypeEnum.ONLINE_OFFLINE_NOTIFY.getType(),userProfileDTO),userProfileDTO.getUserId());

            UserProfileEvent userProfileEvent = new UserProfileEvent(uidOptional.get(), null, NettyUtil.getAttr(channel, NettyUtil.IP),  System.currentTimeMillis());
            UserOfflineEvent userOfflineEvent = new UserOfflineEvent(null,WebsocketResponseTypeEnum.ONLINE_OFFLINE_NOTIFY.getType(),userProfileEvent);
            this.imMQPublisher.publish(MQConstant.USER_OFFLINE_TOPIC,userOfflineEvent);
        }
    }

    @Override
    public void authorize(Channel channel, AuthorizeDTO authorizeDTO) throws BusinessException {
        boolean verifyStatus = this.userSecurityService.tokenVerify(authorizeDTO.getToken());
        if (verifyStatus) {//用户校验成功给用户登录
            Long userId  = this.userSecurityService.getValidUserId(authorizeDTO.getToken());
            UserProfileDTO userProfileDTO = this.userProfileAppService.getUser(userId);
            loginSuccess(channel,userProfileDTO, authorizeDTO.getToken());
        }else {
            //让前端的token失效
            sendMsg(channel,new PushBashDTO<LoginDTO>(WebsocketResponseTypeEnum.INVALIDATE_TOKEN.getType(),null));
        }
    }

    /**
     * (channel必在本地)登录成功，并更新状态
     */
    private void loginSuccess(Channel channel, UserProfileDTO userProfileDTO, String token) {
        //更新上线列表
        container.online(channel, userProfileDTO.getUserId());
        //发送给对应的用户
        LoginUser loginUser = new LoginUser.LoginUserBuild()
                .avatar(userProfileDTO.getAvatar())
                .nickName(userProfileDTO.getNickName())
                .userId(userProfileDTO.getUserId())
                .build();
        LoginDTO loginDTO = new LoginDTO(loginUser, token);
        sendMsg(channel, new PushBashDTO<LoginDTO>(WebsocketResponseTypeEnum.LOGIN_AUTHORIZE_SUCCESS.getType(),loginDTO));
        //发送用户上线事件
        boolean online = container.isOnline(userProfileDTO.getUserId());
        if (!online) {
            UserProfileEvent userProfileEvent = new UserProfileEvent(userProfileDTO.getUserId(), userProfileDTO.getLastLoginTime(), NettyUtil.getAttr(channel, NettyUtil.IP), System.currentTimeMillis());
            UserOnlineEvent userOnlineEvent = new UserOnlineEvent(userProfileDTO.getUserId(), PushTypeEnum.USER.getType(), WebsocketResponseTypeEnum.ONLINE_OFFLINE_NOTIFY.getType(), userProfileEvent);
            this.imMQPublisher.publish(MQConstant.USER_ONLINE_TOPIC,userOnlineEvent);
        }
    }


    @Override
    public void sendToUser(PushBashDTO<?> pushBashDTO, Long userId) {
        CopyOnWriteArrayList<Channel> channels = container.getOnlineUserIdMap().get(userId);
        if (CollectionsUtils.isNullOrEmpty(channels)) {
            logger.info("用户：{}不在线", userId);
            return;
        }
        channels.forEach(channel -> {
            threadPoolTaskExecutor.execute(() -> sendMsg(channel, pushBashDTO));
        });
    }

    /**
     * 给本地channel发送消息
     *
     * @param channel
     * @param pushBashDTO
     */
    private void sendMsg(Channel channel, PushBashDTO<?> pushBashDTO) {
        channel.writeAndFlush(new TextWebSocketFrame(json.toString(pushBashDTO)));
    }
}
