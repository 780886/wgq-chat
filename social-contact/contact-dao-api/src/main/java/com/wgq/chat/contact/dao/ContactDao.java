package com.wgq.chat.contact.dao;

import com.wgq.chat.contact.po.Contact;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ContactDao
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/2 22:13
 * @Version 1.0
 **/
public interface ContactDao {

    Long insert(@Param("contact") Contact contact);

    Contact getContact(@Param("userId") Long userId, @Param("friendId") Long friendId);

    Long removeById(@Param("id") Long id);

    List<Contact> getMyContact(@Param("userId") Long userId);

}
