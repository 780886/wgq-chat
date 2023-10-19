package com.wgq.chat.controller;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.assemble.ChatAssemble;
import com.wgq.chat.bo.MessageReturnBO;
import com.wgq.chat.domain.service.ChatService;
import com.wgq.chat.protocol.param.MessageReadParam;
import com.wgq.chat.protocol.param.MessageRecallParam;
import com.wgq.chat.protocol.param.MessageSendParam;
import com.wgq.chat.vo.MessageReturnVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

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
    public MessageReturnVO sendMessage(@RequestBody MessageSendParam messageSendParam) throws BusinessException {
        Long messageId = this.chatService.sendMessage(messageSendParam);
        MessageReturnBO messageReturnBO = this.chatService.getMessage(messageId);
        //返回完整消息格式，方便前端展示
        return this.chatAssemble.assemble2vo(messageReturnBO);
    }

    @GetMapping("/getMessageList")
    @ApiOperation("消息列表")
    public List<MessageReturnVO> getMessageList(@RequestBody MessageReadParam messageReadParam) throws BusinessException {
        List<MessageReturnBO> messageReturnBOList = this.chatService.getMessageList(messageReadParam);
        return this.chatAssemble.assemble2VOList(messageReturnBOList);
    }

    @PutMapping("/recall")
    @ApiOperation("撤回消息")
//    @FrequencyControl(time = 20, count = 3, target = FrequencyControl.Target.UID)
    public void recallMsg(@RequestBody MessageRecallParam messageRecallParam) throws BusinessException {
        this.chatService.recallMessage(messageRecallParam);
    }

}
