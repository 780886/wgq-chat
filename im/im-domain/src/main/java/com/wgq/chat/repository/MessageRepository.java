package com.wgq.chat.repository;

import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.bo.MessageReturnBO;

/**
 * @ClassName: MessageRepository
 * @Author : wgq
 * @Date :2023/8/23  14:14
 * @Description:
 * @Version :1.0
 */
public interface MessageRepository {

    Long save(MessageBO messageBO);

    MessageReturnBO getMessage(Long messageId, Long userId);

    MessageBO getMessage(Long messageId);

    Integer getGapCount(Long roomId, Long replyMessageId, Long currentSaveMessageId);

    void updateById(MessageBO messageBO);
}
