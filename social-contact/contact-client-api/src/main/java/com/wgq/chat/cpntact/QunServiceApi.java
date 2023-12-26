package com.wgq.chat.cpntact;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.contact.protocol.contact.dto.QunDTO;

import java.util.List;

public interface QunServiceApi {

    QunDTO getQunByRoomId(Long roomId);

}
