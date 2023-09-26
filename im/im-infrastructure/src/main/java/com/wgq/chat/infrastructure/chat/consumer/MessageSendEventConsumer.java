package com.wgq.chat.infrastructure.chat.consumer;

import com.wgq.chat.bo.MessageBO;
import com.wgq.chat.bo.RoomBO;
import com.wgq.chat.bo.RoomFriendBO;
import com.wgq.chat.cpntact.ContactServiceApi;
import com.wgq.chat.mq.ImMQPublisher;
import com.wgq.chat.protocol.constant.MQConstant;
import com.wgq.chat.protocol.dto.PushBashDTO;
import com.wgq.chat.protocol.enums.RoomTypeEnum;
import com.wgq.chat.protocol.enums.WebsocketResponseTypeEnum;
import com.wgq.chat.protocol.event.MessageSendEvent;
import com.wgq.chat.repository.MessageRepository;
import com.wgq.chat.repository.RoomFriendRepository;
import com.wgq.chat.repository.RoomRepository;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: MessageSendConsumer
 * @Author : wgq
 * @Date :2023/8/23  14:53
 * @Description:
 * @Version :1.0
 */
@RocketMQMessageListener(consumerGroup = MQConstant.SEND_MSG_GROUP, topic = MQConstant.SEND_MSG_TOPIC)
@Named
public class MessageSendEventConsumer implements RocketMQListener<MessageSendEvent> {

    @Inject
    private ImMQPublisher imMQPublisher;

    @Inject
    private MessageRepository messageRepository;

    @Inject
    private RoomRepository repository;

    @Inject
    private RoomFriendRepository roomFriendRepository;

    @Inject
    private ContactServiceApi contactServiceApi;

    @Override
    public void onMessage(MessageSendEvent messageSendEvent) {
        MessageBO messageBO = this.messageRepository.getMessage(messageSendEvent.getMessageId());
        RoomBO roomBO = this.repository.getRoom(messageBO.getRoomId());
        if (roomBO.isHotRoom()){
            //TODO 推送所有人
        }else {
            List<Long> memberUserList = new ArrayList<>();
            if (Objects.equals(roomBO.getType(), RoomTypeEnum.GROUP.getType())){//普通群聊推送所有群成员
                //TODO 推送群所有成员
            }else if (Objects.equals(roomBO.getType(),RoomTypeEnum.FRIEND.getType())){//单聊对象
                //推送给单人
                RoomFriendBO roomFriend = this.roomFriendRepository.getRoomFriend(roomBO.getId());
                memberUserList = Arrays.asList(roomFriend.getSmallerUserId(),roomFriend.getLargerUserId());
            }
            //TODO 更新所有群成员的会话时间
//            this.contactServiceApi.refreshOrCreateActiveTime(roomBO.getId(), memberUserList, messageBO.getId(), messageBO.getCreateTime());
            //推送给用户
            this.imMQPublisher.publish(MQConstant.PUSH_TOPIC,new PushBashDTO<>(WebsocketResponseTypeEnum.MESSAGE.getType(),messageBO),memberUserList);
        }
    }
}
