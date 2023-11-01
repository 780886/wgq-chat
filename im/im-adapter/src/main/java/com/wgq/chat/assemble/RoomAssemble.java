package com.wgq.chat.assemble;

import com.sheep.utils.BeanUtils;
import com.wgq.chat.bo.RoomBO;
import com.wgq.chat.protocol.dto.RoomDTO;
import org.springframework.stereotype.Component;

import javax.inject.Named;

/**
 * @ClassName RoomAssemble
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/1 14:25
 * @Version 1.0
 **/
@Named
public class RoomAssemble {
    public RoomDTO assembleRoomDTO(RoomBO roomBO) {
        RoomDTO roomDTO = new RoomDTO();
        BeanUtils.copyProperties(roomBO,roomDTO);
        return roomDTO;
    }
}
