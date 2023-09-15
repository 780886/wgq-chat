package com.wgq.chat.contact.assemble;

import com.wgq.chat.contact.bo.ContactBO;
import com.wgq.passport.protocol.dto.UserProfileDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: ContactAssemble
 * @Author : wgq
 * @Date :2023/9/15  15:42
 * @Description:
 * @Version :1.0
 */
@Component
public class ContactAssemble {

    public List<ContactBO> assembleContactList(Map<Long, UserProfileDTO> userProfileDTOMap) {
        return userProfileDTOMap.entrySet().stream().map(entry -> {
            ContactBO contactBO = new ContactBO();
            contactBO.setUserDto(entry.getValue());
            return contactBO;
        }).collect(Collectors.toList());
    }
}
