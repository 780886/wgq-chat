package com.wgq.chat.infrastructure.persistence;

import com.wgq.chat.dao.RoomUserDao;
import com.wgq.chat.repository.RoomUserRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @ClassName: RoomUserRepostitoryImpl
 * @Author : wgq
 * @Date :2023/10/31  15:01
 * @Description:
 * @Version :1.0
 */
@Named
public class RoomUserRepositoryImpl implements RoomUserRepository {

    @Inject
    private RoomUserDao roomUserDao;

    @Override
    public void refreshOrCreateLastTime(Long roomId, List<Long> userIdList, Long messageId, Long sendTime) {
        this.roomUserDao.refreshOrCreateLastTime(roomId, userIdList, messageId, sendTime);
    }
}
