package com.wgq.chat.contact.dao;

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

    Qun getEntity(Long id);

    List<Qun> getQuns(Long categoryId);

    void delete(Long roomId);

    Integer updateById(Qun qun);

    List<Qun> findEnabledQunList();

    List<Qun> getQuns(Collection<Long> qunIds);

    Qun getOwnerQun(@Param("ownerId") Long ownerId);

    Qun qunDetailByRoomId(@Param("roomId") Long roomId);

}
