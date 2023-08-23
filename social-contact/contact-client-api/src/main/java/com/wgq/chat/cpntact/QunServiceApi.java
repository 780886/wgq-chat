package com.wgq.chat.cpntact;

import com.sheep.protocol.BusinessException;


import java.util.List;

public interface QunServiceApi {
    List<Integer> getMemberById(Long qunId) throws BusinessException;
}
