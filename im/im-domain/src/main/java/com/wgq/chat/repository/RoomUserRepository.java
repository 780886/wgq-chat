package com.wgq.chat.repository;

import java.util.List;

public interface RoomUserRepository {
    void refreshOrCreateLastTime(Long roomId, List<Long> userIdList, Long messageId, Long sendTime);

}
