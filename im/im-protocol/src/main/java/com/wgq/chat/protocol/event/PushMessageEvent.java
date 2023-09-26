package com.wgq.chat.protocol.event;


import com.sheep.mq.MQEvent;
import com.wgq.chat.protocol.dto.PushBashDTO;
import com.wgq.chat.protocol.enums.PushTypeEnum;


public class PushMessageEvent implements MQEvent {


    private PushBashDTO<?> pushBashDTO;

    /**
     * 推送的uid
     */
    private Long userId;


    private Integer pushType;

    public PushMessageEvent() {
    }

    public PushMessageEvent(Long userId, PushBashDTO<?> pushBashDTO) {
        this.userId = userId;
        this.pushBashDTO = pushBashDTO;
        this.pushType = PushTypeEnum.USER.getType();
    }

    public PushMessageEvent(PushBashDTO<?> pushBashDTO) {
        this.pushBashDTO = pushBashDTO;
        this.pushType = PushTypeEnum.ALL.getType();
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public PushBashDTO<?> getPushBashDTO() {
        return pushBashDTO;
    }

    public void setPushBashDTO(PushBashDTO<?> pushBashDTO) {
        this.pushBashDTO = pushBashDTO;
    }

    public Integer getPushType() {
        return pushType;
    }

    public void setPushType(Integer pushType) {
        this.pushType = pushType;
    }
}
