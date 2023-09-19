package com.wgq.chat.dao;

import com.wgq.chat.po.Message;
import org.apache.ibatis.annotations.Param;

public interface MessageDao {

    Long insert(@Param("message") Message message);

    Message getMessage(@Param("messageId") Long messageId, @Param("senderUserId") Long senderUserId);

    Message getMessageById(@Param("messageId") Long messageId);

    Integer getGapCount(Long roomId, Long replyMessageId,Long sendUserId);

    Long updateById(Message message);
}
