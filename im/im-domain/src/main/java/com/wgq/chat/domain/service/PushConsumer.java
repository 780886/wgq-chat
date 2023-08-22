package com.wgq.chat.domain.service;

import com.wgq.chat.protocol.dto.PushMessageDTO;

/**
 * @ClassName: PushConsumer
 * @Author : wgq
 * @Date :2023/8/22  17:09
 * @Description:
 * @Version :1.0
 */
public interface PushConsumer {

    public void onMessage(PushMessageDTO pushMessageDTO);
}
