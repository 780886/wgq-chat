package com.wgq.chat.contact.dao;

import com.sheep.protocol.enums.StatusRecord;
import com.wgq.chat.contact.po.Qun;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @ClassName QunDao
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 22:11
 * @Version 1.0
 **/
public interface QunDao {

    Long insert(@Param("qun") Qun qun);

    void update(@Param("qun") Qun qun);

    Qun getEntity(@Param("id") Long id);

    List<Qun> getQuns(@Param("categoryId") Long categoryId);

    void delete(@Param("roomId") Long roomId);

    Integer updateById(@Param("qun") Qun qun);

    List<Qun> getQunsByIds(@Param("qunIds") Collection<Long> qunIds);

    Qun getOwnerQun(@Param("ownerId") Long ownerId);

    Qun qunDetailByRoomId(@Param("roomId") Long roomId);

    List<Qun> getQunsByStatus(StatusRecord enable);
}
