package com.wgq.chat.protocol.param;

import com.sheep.protocol.Param;

/**
 * @ClassName: MessageRecallParam
 * @Author : wgq
 * @Date :2023/10/19  11:04
 * @Description:
 * @Version :1.0
 */
public class MessageRecallParam implements Param {

    /**
     * 消息id
     */
    private Long messageId;
    /**
     * 房间id
     */
    private Long roomId;

    public Long getMessageId() {
        return messageId;
    }

    public Long getRoomId() {
        return roomId;
    }
}
