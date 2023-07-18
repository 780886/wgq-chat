package com.wgq.chat.domain.service;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.domain.netty.CancelProtocol;
import com.wgq.chat.domain.netty.Protocol;
import com.wgq.chat.domain.netty.UserContainer;
import com.wgq.chat.protocol.ChatSession;
import com.wgq.chat.protocol.constant.Chat;
import com.wgq.chat.protocol.dto.*;
import com.wgq.chat.protocol.param.MessageCancelParam;
import com.wgq.chat.protocol.param.MessageReadParam;
import com.wgq.chat.repository.ContactRepository;
import com.wgq.chat.repository.MessageRepository;
import com.wgq.chat.repository.SessionRepository;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
public class ChatService {

//    @Inject
    private MessageRepository messageRepository;
//    @Inject
    private SessionRepository sessionRepository;
//    @Inject
    private ContactRepository contactsRepository;

    public ContactsDTO getContacts(Integer userId) {
        List<QunDTO> quns = this.contactsRepository.getQunsByUserId(userId);
        List<UserDTO> users = this.contactsRepository.getFriendsByUserId(userId);
        return new ContactsDTO(quns, users);
    }

    public void saveMessage(Protocol protocol) {
        //将消息保存至session 每个消息只存留一份，只保留最近100条
        this.messageRepository.saveMessage(protocol);
        this.sessionRepository.saveSession(protocol.getChatSession(),protocol.getSender());
    }

    public void read(MessageReadParam messageRead) throws BusinessException {
        this.messageRepository.read(messageRead);
    }

    public void cancel(MessageCancelParam messageCancel) throws BusinessException {
        CancelProtocol cancelProtocol = new CancelProtocol(messageCancel.getSessionKey(), messageCancel.getClientSendTime());
        //将会话的消息移除
        this.messageRepository.cancel(messageCancel);
        List<Channel> channels;
        if (Chat.CHAT_TYPE_1_2_1 == messageCancel.getChatType()) {
            ChatSession chatSession = ChatSession.create1To1CancelSession(messageCancel.getSender(), messageCancel.getSessionKey());
            channels = UserContainer.getContainer().getChannels(chatSession,messageCancel.getSender());
        } else {
            ChatSession chatSession = ChatSession.createQunSession(messageCancel.getSender(), messageCancel.getSessionKey());
            channels = UserContainer.getContainer().getChannels(chatSession,messageCancel.getSender());
        }
        for (Channel channel : channels) {
            if (channel == null || !channel.isOpen() || !channel.isActive()) {
                continue;
            }
            channel.writeAndFlush(new BinaryWebSocketFrame(cancelProtocol.toBytes()));
        }
    }

    public List<SessionDTO> fetchSessions(Integer userId) {
        List<ChatSession> chatSessions = this.sessionRepository.getSessions(userId);
        List<SessionDTO> sessions = new ArrayList<>(chatSessions.size());
        List<String> sessionKeys = new ArrayList<>(chatSessions.size());
        for (ChatSession session : chatSessions) {
            List<MessageDTO> messages = this.messageRepository.getMessageBySession(session.getSessionKey());
            sessionKeys.add(session.getSessionKey());
            sessions.add(new SessionDTO(session, messages));
        }
        Map<String, Long> lastReadMap = this.messageRepository.getLastRead(userId, sessionKeys);
        for (SessionDTO session : sessions) {
            String sessionKey = session.getChatSession().getSessionKey();
            if(lastReadMap.containsKey(sessionKey)){
                session.setLastReadTime(lastReadMap.get(sessionKey));
            }
        }
        return sessions;
    }
}
