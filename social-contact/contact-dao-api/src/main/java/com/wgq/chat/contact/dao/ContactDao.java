package com.wgq.chat.contact.dao;

import com.wgq.chat.contact.po.Contact;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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

    Contact findContact(Long userId, Long friendId);

    Long removeById(Long id);

    List<Contact> getMyContact(Long userId);

    void refreshOrCreateActiveTime(Long roomId, List<Long> memberUserList, Long messageId, Date activeTime);

}
