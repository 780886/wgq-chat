package com.wgq.chat.infrastructure.service;

import com.sheep.core.spi.JsonFactory;
import com.sheep.json.Json;
import com.sheep.protocol.BusinessException;
import com.sheep.redis.constant.RedisKey;
import com.sheep.redis.frequency.annotation.FrequencyControl;
import com.sheep.redis.utils.RedisUtils;
import com.sheep.utils.CollectionsUtils;
import com.wgq.chat.domain.event.UserOfflineEvent;
import com.wgq.chat.domain.netty.UserContainer;
import com.wgq.chat.domain.service.WebSocketService;
import com.wgq.chat.protocol.dto.AuthorizeDTO;
import com.wgq.chat.protocol.dto.ChannelExtraDTO;
import com.wgq.chat.protocol.dto.PushBashDTO;
import com.wgq.chat.protocol.enums.RespTypeEnum;
import com.wgq.chat.protocol.vo.LoginUrlVO;
import com.wgq.passport.api.UserLoginService;
import com.wgq.passport.api.UserProfileAppService;
import com.wgq.passport.protocol.dto.LoginDTO;
import com.wgq.passport.protocol.dto.UserProfileDTO;
import com.wgq.passport.protocol.query.login.LoginQuery;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
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
    private UserLoginService userLoginService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @FrequencyControl(time = 1000, count = 50, spEl = "T(com.wgq.chat.domain.netty.NettyUtil).getAttr(#channel,T(com.wgq.chat.domain.netty.NettyUtil).IP)")
    @Override
    public void handleLoginReq(Channel channel) {
        LoginUrlVO loginUrlVO = new LoginUrlVO.LoginUrlVOBuilder()
                .loginUrl("www.chat.com/login")//登录url
                .build();
        sendMsg(channel,new PushBashDTO<LoginUrlVO>(RespTypeEnum.LOGIN_URL.getType(),loginUrlVO));
    }

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
            UserProfileDTO userProfileDTO = new UserProfileDTO();
            userProfileDTO.setUserId(uidOptional.get());
            userProfileDTO.setGmtModified(System.currentTimeMillis());
            applicationEventPublisher.publishEvent(new UserOfflineEvent(this, userProfileDTO));
        }
    }

    @Override
    public void authorize(Channel channel, AuthorizeDTO authorizeDTO) throws BusinessException {
        boolean verifyStatus = this.userProfileAppService.verify(authorizeDTO.getToken());
        if (verifyStatus) {//用户校验成功给用户登录
            Long userId  = this.userProfileAppService.getValidUserId(authorizeDTO.getToken());
            UserProfileDTO userProfileDTO = this.userProfileAppService.getUser(userId);
            loginSuccess(channel,userProfileDTO, authorizeDTO.getToken());
        }else {
            //让前端的token失效
            sendMsg(channel,new PushBashDTO<LoginDTO>(RespTypeEnum.INVALIDATE_TOKEN.getType(),null));
        }
    }

    /**
     * (channel必在本地)登录成功，并更新状态
     */
    private void loginSuccess(Channel channel, UserProfileDTO userProfileDTO, String token) {
        //更新上线列表
        container.online(channel, userProfileDTO.getUserId());
        //返回给用户登录成功
//        boolean hasPower = true;
//        boolean hasPower = iRoleService.hasPower(user.getId(), RoleEnum.CHAT_MANAGER);
        logger.info("用户登录成功,用户id:{},",userProfileDTO.getUserId());
        //TODO 以下适用于群聊
        //发送给对应的用户
//        LoginUser loginUser = new LoginUser.LoginUserBuild()
//                .avatar(userProfileDTO.getAvatar())
//                .nickName(userProfileDTO.getNickName())
//                .userId(userProfileDTO.getUserId())
//                .build();
//        LoginDTO loginDTO = new LoginDTO(loginUser, token);
//        sendMsg(channel, new PushBashDTO<>(RespTypeEnum.LOGIN_SUCCESS.getType(),loginDTO));
//        //发送用户上线事件
//        boolean online = isOnline(userProfileDTO.getUserId());
//        if (!online) {
//            userProfileDTO.setGmtModified(System.currentTimeMillis());
//            userProfileDTO.setIp(NettyUtil.getAttr(channel, NettyUtil.IP));
//            applicationEventPublisher.publishEvent(new UserOnlineEvent(this, userProfileDTO));
//        }
    }

    @Override
    public Boolean scanLoginSuccess(Integer loginCode, Long userId) throws BusinessException {
        if (userId == null){
            return Boolean.FALSE;
        }
        LoginQuery loginQuery = new LoginQuery();
        loginQuery.setUserName("wgq");
        LoginDTO loginDTO = this.userLoginService.login(loginQuery,null);
        loginSuccess(null,null,loginDTO.getToken());
        return Boolean.TRUE;
    }


    public boolean isOnline(Long userId) {
        String onlineKey = RedisKey.getKey(RedisKey.ONLINE_UID_ZET);
        return RedisUtils.zIsMember(onlineKey, userId);
    }

    @Override
    public void sendToUid(PushBashDTO<?> pushBashDTO, Long userId) {
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
