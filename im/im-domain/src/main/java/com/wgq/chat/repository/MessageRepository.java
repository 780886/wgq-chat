package com.wgq.chat.repository;

import com.sparrow.protocol.BusinessException;
import com.wgq.chat.domain.netty.Protocol;
import com.wgq.chat.protocol.dto.MessageDTO;
import com.wgq.chat.protocol.param.MessageCancelParam;
import com.wgq.chat.protocol.param.MessageReadParam;

import java.util.List;
import java.util.Map;

public interface MessageRepository {
    void cancel(MessageCancelParam messageCancel) throws BusinessException;

    String saveImageContent(Protocol content);

    void saveMessage(Protocol message);

    void read(MessageReadParam messageRead);

    Map<String, Long> getLastRead(Integer me, List<String> sessionKeys);

    List<MessageDTO> getMessageBySession(String session);
}
