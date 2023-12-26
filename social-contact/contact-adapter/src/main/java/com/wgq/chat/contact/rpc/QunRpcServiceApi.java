package com.wgq.chat.contact.rpc;

import com.wgq.chat.contact.protocol.contact.dto.QunDTO;
import com.wgq.chat.contact.service.QunService;
import com.wgq.chat.cpntact.QunServiceApi;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @ClassName QunRpcServiceApi
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/7 14:48
 * @Version 1.0
 **/
@Named
public class QunRpcServiceApi implements QunServiceApi {

    @Inject
    private QunService qunService;

    @Override
    public QunDTO getQunByRoomId(Long roomId) {
        return this.qunService.getQunByRoomId(roomId);
    }
}
