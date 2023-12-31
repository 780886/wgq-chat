package com.wgq.chat.assemble;

import com.sheep.utils.BeanUtils;
import com.sheep.utils.CollectionsUtils;
import com.wgq.chat.bo.MessageReturnBO;
import com.wgq.chat.vo.MessageReturnVO;

import javax.inject.Named;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        BeanUtils.copyProperties(messageReturnBO,messageReturnVO);
        return messageReturnVO;
    }

    public List<MessageReturnVO> assemble2VOList(List<MessageReturnBO> messageReturnBOList) {
        if (CollectionsUtils.isNullOrEmpty(messageReturnBOList)){
            return Collections.emptyList();
        }
        return messageReturnBOList.stream().map(this::assemble2vo).collect(Collectors.toList());
    }
}
