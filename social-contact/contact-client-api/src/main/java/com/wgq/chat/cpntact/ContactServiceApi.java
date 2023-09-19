package com.wgq.chat.cpntact;

import java.util.Date;
import java.util.List;

public interface ContactServiceApi {

    void refreshOrCreateActiveTime(Long roomId, List<Long> memberUserList, Long messageId, Date activeTime);
}
