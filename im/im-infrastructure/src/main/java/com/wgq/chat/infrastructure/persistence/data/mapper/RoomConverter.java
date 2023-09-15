package com.wgq.chat.infrastructure.persistence.data.mapper;

import com.wgq.chat.bo.RoomBO;
import com.wgq.chat.po.Room;
import com.wgq.chat.protocol.enums.HotFlagEnum;
import com.wgq.chat.protocol.enums.RoomTypeEnum;
import org.springframework.beans.BeanUtils;

import javax.inject.Named;

/**
 * @ClassName: RoomConverter
 * @Author : wgq
 * @Date :2023/9/15  11:25
 * @Description:
 * @Version :1.0
 */
@Named
public class RoomConverter {
    public Room convert2po(RoomTypeEnum roomTypeEnum) {
        Room room = new Room();
        room.setType(roomTypeEnum.getType());
        room.setHotFlag(HotFlagEnum.NOT.getType());
        return room;
    }

}
