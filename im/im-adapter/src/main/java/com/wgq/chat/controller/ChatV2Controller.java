package com.wgq.chat.controller;

import com.sheep.protocol.BusinessException;
import com.sheep.protocol.ClientInformation;
import com.sheep.protocol.LoginUser;
import com.sheep.protocol.ThreadContext;
import com.sheep.support.Authenticator;
import com.wgq.chat.domain.netty.UserContainer;
import com.wgq.chat.domain.service.ChatService;
import com.wgq.chat.protocol.dto.ContactsDTO;
import com.wgq.chat.protocol.dto.SessionDTO;
import com.wgq.chat.protocol.param.MessageCancelParam;
import com.wgq.chat.protocol.param.MessageReadParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/chat/v2")
public class ChatV2Controller {

    private static Logger logger = LoggerFactory.getLogger(ChatV2Controller.class);

    @Inject
    private ChatService chatService;

//    @Inject
    private Authenticator authenticator;

    @RequestMapping("/get-user-id")
    public Integer getUserId(String token) throws BusinessException {
        ClientInformation client= ThreadContext.getClientInfo();
        return this.authenticator.authenticate(token, client.getDeviceId()).getUserId().intValue();
    }

    @RequestMapping("/is-online")
    public Boolean online(LoginUser loginUser) {
        //LoginUser loginUser = ThreadContext.getLoginToken();
        int userId = loginUser.getUserId().intValue();
        return UserContainer.getContainer().online(String.valueOf(userId));
    }

    @CrossOrigin(origins = {"*"})
    @PostMapping("/contacts")
    public ContactsDTO getContactsList() {
        LoginUser loginUser = ThreadContext.getLoginToken();
        return chatService.getContacts(loginUser.getUserId().intValue());
    }

    @PostMapping("/session/read")
    public Boolean readSession(@RequestBody MessageReadParam messageRead) throws BusinessException {
        LoginUser loginUser = ThreadContext.getLoginToken();
        int userId = loginUser.getUserId().intValue();
        messageRead.setUserId(userId);
        chatService.read(messageRead);
        return true;
    }

    @PostMapping("/sessions")
    public List<SessionDTO> getSessions() throws BusinessException {
        LoginUser loginUser = ThreadContext.getLoginToken();
        int userId = loginUser.getUserId().intValue();
        return chatService.fetchSessions(userId);
    }

    @PostMapping("/cancel")
    public Boolean cancel(@RequestBody MessageCancelParam messageCancel) {
        try {
            int userId = ThreadContext.getLoginToken().getUserId().intValue();
            messageCancel.setSender(userId);
            chatService.cancel(messageCancel);
        } catch (Exception e) {
            logger.error("cancel error", e);
            return false;
        }
        return true;
    }
}
