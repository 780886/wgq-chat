package com.wgq.chat.infrastructure.persistence;

import com.wgq.chat.bo.RoomBO;
import com.wgq.chat.dao.RoomDao;
import com.wgq.chat.infrastructure.persistence.data.mapper.RoomConverter;
import com.wgq.chat.po.Room;
import com.wgq.chat.protocol.enums.RoomTypeEnum;
import com.wgq.chat.repository.RoomRepository;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @ClassName: RoomRepositoryImpl
 * @Author : wgq
 * @Date :2023/9/15  11:15
 * @Description:
 * @Version :1.0
 */
@Named
public class RoomRepositoryImpl implements RoomRepository {


    @Inject
    private RoomDao roomDao;

    @Inject
    private RoomConverter roomConverter;

    @Override
    public Long createRoom(RoomTypeEnum roomTypeEnum) {
        Room room = this.roomConverter.convert2po(roomTypeEnum);
        return this.roomDao.insert(room);
    }

    @Override
    public RoomBO getRoom(Long roomId) {
        Room room = this.roomDao.getById(roomId);
        return this.roomConverter.convert2RoomBO(room);
    }
}
