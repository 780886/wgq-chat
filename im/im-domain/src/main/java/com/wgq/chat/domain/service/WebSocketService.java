package com.wgq.chat.domain.service;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.protocol.dto.AuthorizeDTO;
import com.wgq.chat.protocol.dto.PushBashDTO;
import io.netty.channel.Channel;

public interface WebSocketService {
    /**
     * 处理用户登录请求，需要返回一张带code的二维码
     *
     * @param channel
     */
    void handleLoginReq(Channel channel);

    /**
     * 处理所有ws连接的事件
     *
     * @param channel
     */
    void connect(Channel channel);

    /**
     * 处理ws断开连接的事件
     *
     * @param channel
     */
    void removed(Channel channel);

    /**
     * 主动认证登录
     *
     * @param channel
     * @param authorizeDTO
     */
    void authorize(Channel channel, AuthorizeDTO authorizeDTO) throws BusinessException;

    /**
     * 扫码用户登录成功通知,清除本地Cache中的loginCode和channel的关系
     */
    Boolean scanLoginSuccess(Integer loginCode, Long uid);

    /**
     * 通知用户扫码成功
     *
     * @param loginCode
     */
    Boolean scanSuccess(Integer loginCode);

    /**
     * 推动消息给所有在线的人
     *
     * @param pushBashDTO 发送的消息体
     * @param skipUid    需要跳过的人
     */
//    void sendToAllOnline(PushBashDTO<?> pushBashDTO, Long skipUid);

    /**
     * 推动消息给所有在线的人
     *
     * @param pushBashDTO 发送的消息体
     */
//    void sendToAllOnline(WSBaseResp<?> wsBaseResp);

    void sendToUid(PushBashDTO<?> pushBashDTO, Long userId);

}
