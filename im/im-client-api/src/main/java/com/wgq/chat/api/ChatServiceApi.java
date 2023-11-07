package com.wgq.chat.api;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.protocol.param.MessageSendParam;

public interface ChatServiceApi {

    Long sendMessage(MessageSendParam messageSendParam,Long userId) throws BusinessException;

    Long sendMessage(MessageSendParam messageSendParam) throws BusinessException;


}
