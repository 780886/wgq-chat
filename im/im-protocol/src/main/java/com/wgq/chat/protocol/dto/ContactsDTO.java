package com.wgq.chat.protocol.dto;

import com.sheep.protocol.VO;
import com.wgq.chat.contact.protocol.contact.dto.QunDTO;
import com.wgq.chat.contact.protocol.contact.dto.UserDTO;

import java.util.List;

public class ContactsDTO implements VO {

    public ContactsDTO(List<QunDTO> quns, List<UserDTO> users) {
        this.quns = quns;
        this.users = users;
    }

    private List<QunDTO> quns;
    private List<UserDTO> users;

    public List<QunDTO> getQuns() {
        return quns;
    }

    public void setQuns(List<QunDTO> quns) {
        this.quns = quns;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
