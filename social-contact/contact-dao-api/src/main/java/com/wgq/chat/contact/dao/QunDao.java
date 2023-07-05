package com.wgq.chat.contact.dao;

import com.wgq.chat.contact.po.Qun;

import java.util.List;

/**
 * @ClassName QunDao
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 22:11
 * @Version 1.0
 **/
public interface QunDao {

    Long insert(Qun qun);

    void update(Qun qun);

    Qun getEntity(Long id);

    List<Qun> getQuns(Long categoryId);


    void delete(Long qunId);

    Integer updateById(Qun qun);

}
