package com.wgq.chat.assemble;

import com.wgq.chat.bo.MessageReturnBO;
import com.wgq.chat.vo.MessageReturnVO;

import javax.inject.Named;

/**
 * @ClassName: ChatAssemble
 * @Author : wgq
 * @Date :2023/8/23  16:34
 * @Description:
 * @Version :1.0
 */
@Named
public class ChatAssemble {
    public MessageReturnVO assemble2vo(MessageReturnBO messageReturnBO) {
        MessageReturnVO messageReturnVO = new MessageReturnVO();
        return messageReturnVO;
    }
}