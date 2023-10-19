package com.wgq.chat.protocol.event;

import com.sheep.mq.MQEvent;

/**
 * @ClassName: MessageRecallEvent
 * @Author : wgq
 * @Date :2023/10/19  11:40
 * @Description:
 * @Version :1.0
 */
public class MessageRecallEvent implements MQEvent {

    /**
     * ws推送给前端的消息
     *
     * @see com.wgq.chat.protocol.enums.WebsocketResponseTypeEnum
     */
    private Integer type;
    /**
     * 消息id
     */
    private Long messageId;
    /**
     * 房间id
     */
    private Long roomId;
    /**
     * 撤回的用户
     */
    private Long recallUserId;

    public MessageRecallEvent() {
    }

    public MessageRecallEvent(Integer type, Long messageId, Long roomId, Long recallUserId) {
        this.type = type;
        this.messageId = messageId;
        this.roomId = roomId;
        this.recallUserId = recallUserId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getRecallUserId() {
        return recallUserId;
    }

    public void setRecallUserId(Long recallUserId) {
        this.recallUserId = recallUserId;
    }
}
