package com.wgq.chat.controller;

import com.wgq.chat.assemble.ChatAssemble;
import com.wgq.chat.bo.MessageReturnBO;
import com.wgq.chat.domain.service.ChatService;
import com.wgq.chat.protocol.param.MessageSendParam;
import com.wgq.chat.vo.MessageReturnVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * @ClassName: MsgController
 * @Author : wgq
 * @Date :2023/8/23  9:52
 * @Description:
 * @Version :1.0
 */
@RequestMapping("/chat")
@RestController
public class ChatController {

    @Inject
    private ChatService chatService;

    @Inject
    private ChatAssemble chatAssemble;

    @PostMapping("/sendMessage")
    @ApiOperation("发送消息")
    public MessageReturnVO sendMessage(@RequestBody MessageSendParam messageSendParam) {
        MessageReturnBO messageReturnBO = this.chatService.sendMessage(messageSendParam);
        //返回完整消息格式，方便前端展示
        return this.chatAssemble.assemble2vo(messageReturnBO);
    }

}
