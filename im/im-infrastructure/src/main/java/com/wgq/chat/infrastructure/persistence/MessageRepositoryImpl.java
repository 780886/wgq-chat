package com.wgq.chat.infrastructure.persistence;

import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.bo.MessageReturnBO;
import com.wgq.chat.dao.MessageDao;
import com.wgq.chat.infrastructure.persistence.data.mapper.MessageConverter;
import com.wgq.chat.po.Message;
import com.wgq.chat.repository.MessageRepository;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @ClassName: MessageRepositoryImpl
 * @Author : wgq
 * @Date :2023/8/23  14:14
 * @Description:
 * @Version :1.0
 */
@Named
public class MessageRepositoryImpl implements MessageRepository {

    @Inject
    private MessageDao messageDao;

    @Inject
    private MessageConverter messageConverter;

    @Override
    public Long save(MessageBO messageBO) {
        Message message = this.messageConverter.convert2po(messageBO);
        return this.messageDao.insert(message);
    }

    @Override
    public MessageReturnBO getMessage(Long messageId, Long userId) {
        Message message = this.messageDao.getMessage(messageId, userId);
        return this.messageConverter.convert2MessageReturnBO(message);
    }

    @Override
    public MessageBO getMessage(Long messageId) {
        Message message = this.messageDao.getMessageById(messageId);
        return this.messageConverter.convert2bo(message);
    }
}