package com.wgq.chat.rpc;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.api.ChatServiceApi;
import com.wgq.chat.domain.service.ChatService;
import com.wgq.chat.protocol.param.MessageSendParam;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @ClassName: ChatServerRpcApi
 * @Author : wgq
 * @Date :2023/9/14  16:38
 * @Description:
 * @Version :1.0
 */
@Named
public class ChatServerRpcApi implements ChatServiceApi {

    @Inject
    private ChatService chatService;

    @Override
    public Long sendMessage(MessageSendParam messageSendParam, Long userId) throws BusinessException {
        return this.chatService.sendMessage(messageSendParam,userId);
    }
}
