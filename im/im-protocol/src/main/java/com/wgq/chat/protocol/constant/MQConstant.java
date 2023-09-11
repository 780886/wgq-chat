package com.wgq.chat.protocol.constant;

/**
 * @author zhongzb create on 2021/06/10
 */
public interface MQConstant {

    /**
     * 用户上线发送mq
     */
    String USER_ONLINE_TOPIC = "user_online_send_msg";
    String USER_ONLINE_GROUP = "user_online_send_group";

    /**
     * 用户下线发送mq
     */
    String USER_OFFLINE_TOPIC = "user_offline_send_msg";
    String USER_OFFLINE_GROUP = "user_offline_send_group";

    /**
     * 消息发送mq
     */
    String SEND_MSG_TOPIC = "chat_send_msg";
    String SEND_MSG_GROUP = "chat_send_msg_group";

    /**
     * push用户
     */
    String PUSH_TOPIC = "websocket_push";
    String PUSH_GROUP = "websocket_push_group";

    /**
     * (授权完成后)登录信息mq
     */
    String LOGIN_MSG_TOPIC = "user_login_send_msg";
    String LOGIN_MSG_GROUP = "user_login_send_msg_group";

}
