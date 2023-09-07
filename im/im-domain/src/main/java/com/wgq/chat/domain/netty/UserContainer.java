package com.wgq.chat.domain.netty;

import com.sheep.utils.CollectionsUtils;
import com.wgq.chat.protocol.dto.ChannelExtraDTO;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName UserContainer
 * @Description TODO
 * @Author wgq
 * @Date 2023/9/4 23:39
 * @Version 1.0
 **/
public class UserContainer {

    private static Logger logger = LoggerFactory.getLogger(UserContainer.class);
//    /**
//     * channel属性
//     */
//    public static final AttributeKey<String> USER_ID_KEY = AttributeKey.newInstance("userId");

    private UserContainer() {
    }

    private static UserContainer userContainer = new UserContainer();

    public static UserContainer getContainer() {
        return userContainer;
    }

    /**
     * 所有在线的用户和对应的socket
     */
    private static final ConcurrentHashMap<Long, CopyOnWriteArrayList<Channel>> ONLINE_USER_ID_MAP = new ConcurrentHashMap<>();

    /**
     * 所有已连接的websocket连接列表和一些额外参数
     */
    private static final ConcurrentHashMap<Channel, ChannelExtraDTO> ONLINE_WS_MAP = new ConcurrentHashMap<>();

    public  ConcurrentHashMap<Channel, ChannelExtraDTO> getOnlineMap() {
        return ONLINE_WS_MAP;
    }

    public ConcurrentHashMap<Long, CopyOnWriteArrayList<Channel>> getOnlineUserIdMap() {
        return ONLINE_USER_ID_MAP;
    }


    /**
     * 用户上线
     */
    public void online(Channel channel, Long userId) {
        getOrInitChannelExt(channel).setUserId(userId);
        ONLINE_USER_ID_MAP.putIfAbsent(userId, new CopyOnWriteArrayList<>());
        ONLINE_USER_ID_MAP.get(userId).add(channel);
        NettyUtil.setAttr(channel, NettyUtil.USER_ID, userId);
    }

    public void online(Long uid, long optTime) {
        logger.info("更新线上表...");
//        String onlineKey = RedisKey.getKey(RedisKey.ONLINE_UID_ZET);
//        String offlineKey = RedisKey.getKey(RedisKey.OFFLINE_UID_ZET);
//        //移除离线表
//        RedisUtils.zRemove(offlineKey, uid);
//        //更新上线表
//        RedisUtils.zAdd(onlineKey, uid, optTime.getTime());
    }




    /**
     * 如果在线列表不存在，就先把该channel放进在线列表
     *
     * @param channel
     * @return
     */
    public ChannelExtraDTO getOrInitChannelExt(Channel channel) {
        ChannelExtraDTO channelExtraDTO =
                ONLINE_WS_MAP.getOrDefault(channel, new ChannelExtraDTO());
        ChannelExtraDTO old = ONLINE_WS_MAP.putIfAbsent(channel, channelExtraDTO);
        return old == null ? channelExtraDTO : old;
    }

    /**
     * 用户下线
     * return 是否全下线成功
     */
    public boolean offline(Channel channel, Optional<Long> uidOptional) {
        ONLINE_WS_MAP.remove(channel);
        if (uidOptional.isPresent()) {
            CopyOnWriteArrayList<Channel> channels = ONLINE_USER_ID_MAP.get(uidOptional.get());
            if (!CollectionsUtils.isNullOrEmpty(channels)) {
                channels.removeIf(ch -> Objects.equals(ch, channel));
            }
            return CollectionsUtils.isNullOrEmpty(ONLINE_USER_ID_MAP.get(uidOptional.get()));
        }
        return true;
    }


}
