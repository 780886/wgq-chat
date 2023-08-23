package com.wgq.chat.infrastructure.service;

import com.sheep.core.spi.JsonFactory;
import com.sheep.json.Json;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.LoginUser;
import com.sheep.redis.frequency.annotation.FrequencyControl;
import com.sheep.utils.CollectionsUtils;
import com.wgq.chat.domain.listener.UserOfflineEvent;
import com.wgq.chat.domain.netty.NettyUtil;
import com.wgq.chat.domain.service.WebSocketService;
import com.wgq.chat.protocol.dto.AuthorizeDTO;
import com.wgq.chat.protocol.dto.ChannelExtraDTO;
import com.wgq.chat.protocol.dto.PushBashDTO;
import com.wgq.chat.protocol.enums.RespTypeEnum;
import com.wgq.chat.protocol.vo.LoginUrlVO;
import com.wgq.passport.api.UserProfileAppService;
import com.wgq.passport.protocol.dto.LoginDTO;
import com.wgq.passport.protocol.dto.UserProfileDTO;
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
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
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

    /**
     * 所有已连接的websocket连接列表和一些额外参数
     */
    private static final ConcurrentHashMap<Channel, ChannelExtraDTO> ONLINE_WS_MAP = new ConcurrentHashMap<>();

    /**
     * 所有在线的用户和对应的socket
     */
    private static final ConcurrentHashMap<Long, CopyOnWriteArrayList<Channel>> ONLINE_UID_MAP = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<Channel, ChannelExtraDTO> getOnlineMap() {
        return ONLINE_WS_MAP;
    }


    @Inject
    @Qualifier("websocketExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Inject
    private UserProfileAppService userProfileAppService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @FrequencyControl(time = 1000, count = 50, spEl = "T(com.wgq.chat.domain.netty.NettyUtil).getAttr(#channel,T(com.wgq.chat.domain.netty.NettyUtil).IP)")
    @Override
    public void handleLoginReq(Channel channel) {
        LoginUrlVO loginUrlVO = new LoginUrlVO.LoginUrlVOBuilder()
                .loginUrl("www.baidu.com")
                .build();
        sendMsg(channel,new PushBashDTO<LoginUrlVO>(RespTypeEnum.LOGIN_URL.getType(),loginUrlVO));
    }

    @Override
    public void connect(Channel channel) {
        ONLINE_WS_MAP.put(channel, new ChannelExtraDTO());
    }

    @Override
    public void removed(Channel channel) {
        ChannelExtraDTO channelExtraDTO = ONLINE_WS_MAP.get(channel);
        Optional<Long> uidOptional = Optional.ofNullable(channelExtraDTO)
                .map(ChannelExtraDTO::getUserId);
        boolean offlineAll = offline(channel, uidOptional);
        if (uidOptional.isPresent() && offlineAll) {//已登录用户断连,并且全下线成功

            LoginUser loginUser = new LoginUser
                    .LoginUserBuild()
                    .userId(uidOptional.get()).build();
            applicationEventPublisher.publishEvent(new UserOfflineEvent(this, loginUser));
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
        online(channel, userProfileDTO.getUserId());
        //返回给用户登录成功
//        boolean hasPower = iRoleService.hasPower(user.getId(), RoleEnum.CHAT_MANAGER);
        //发送给对应的用户
//        sendMsg(channel, WSAdapter.buildLoginSuccessResp(user, token, hasPower));
        //发送用户上线事件
//        boolean online = userCache.isOnline(user.getId());
//        if (!online) {
//            user.setLastOptTime(new Date());
//            user.refreshIp(NettyUtil.getAttr(channel, NettyUtil.IP));
//            applicationEventPublisher.publishEvent(new UserOnlineEvent(this, user));
//        }
    }

    @Override
    public Boolean scanLoginSuccess(Integer loginCode, Long uid) {
        return null;
    }

    @Override
    public Boolean scanSuccess(Integer loginCode) {
        return null;
    }




    /**
     * 用户上线
     */
    private void online(Channel channel, Long userId) {
        getOrInitChannelExt(channel).setUserId(userId);
        ONLINE_UID_MAP.putIfAbsent(userId, new CopyOnWriteArrayList<>());
        ONLINE_UID_MAP.get(userId).add(channel);
        NettyUtil.setAttr(channel, NettyUtil.UID, userId);
    }

    /**
     * 用户下线
     * return 是否全下线成功
     */
    private boolean offline(Channel channel, Optional<Long> uidOptional) {
        ONLINE_WS_MAP.remove(channel);
        if (uidOptional.isPresent()) {
            CopyOnWriteArrayList<Channel> channels = ONLINE_UID_MAP.get(uidOptional.get());
            if (!CollectionsUtils.isNullOrEmpty(channels)) {
                channels.removeIf(ch -> Objects.equals(ch, channel));
            }
            return CollectionsUtils.isNullOrEmpty(ONLINE_UID_MAP.get(uidOptional.get()));
        }
        return true;
    }

    /**
     * 如果在线列表不存在，就先把该channel放进在线列表
     *
     * @param channel
     * @return
     */
    private ChannelExtraDTO getOrInitChannelExt(Channel channel) {
        ChannelExtraDTO channelExtraDTO =
                ONLINE_WS_MAP.getOrDefault(channel, new ChannelExtraDTO());
        ChannelExtraDTO old = ONLINE_WS_MAP.putIfAbsent(channel, channelExtraDTO);
        return old == null ? channelExtraDTO : old;
    }

    @Override
    public void sendToUid(PushBashDTO<?> pushBashDTO, Long userId) {
        CopyOnWriteArrayList<Channel> channels = ONLINE_UID_MAP.get(userId);
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
