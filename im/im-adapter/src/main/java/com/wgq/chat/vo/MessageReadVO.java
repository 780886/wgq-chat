package com.wgq.chat.vo;

import com.sheep.protocol.VO;

import java.util.List;

/**
 * @ClassName: MessageReadVO
 * @Author : wgq
 * @Date :2023/10/20  14:00
 * @Description:
 * @Version :1.0
 */
public class MessageReadVO implements VO {

    private List<MessageReturnVO> messageReturnVOList;

    public List<MessageReturnVO> getMessageReturnVOList() {
        return messageReturnVOList;
    }

    public void setMessageReturnVOList(List<MessageReturnVO> messageReturnVOList) {
        this.messageReturnVOList = messageReturnVOList;
    }
}
