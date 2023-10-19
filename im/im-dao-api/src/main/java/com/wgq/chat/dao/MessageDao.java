package com.wgq.chat.dao;

import com.wgq.chat.po.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageDao {

    Long insert(@Param("message") Message message);

    Message getMessage(@Param("messageId") Long messageId, @Param("senderUserId") Long senderUserId);

    Message getMessageById(@Param("messageId") Long messageId);

    Integer getGapCount(Long roomId, Long replyMessageId,Long messageId);

    Long updateById(@Param("message") Message message);

    List<Message> getMessageList(@Param("roomId") Long roomId, @Param("status") Integer status, @Param("lastMsgId") Long lastMsgId);
}
