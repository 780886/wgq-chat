package com.wgq.chat.protocol.dto;



import com.sheep.protocol.POJO;
import com.wgq.chat.protocol.enums.PushTypeEnum;

public class PushMessageDTO implements POJO {


    private PushBashDTO<?> pushBashDTO;

    /**
     * 推送的uid
     */
    private Long userId;


    private Integer pushType;

    public PushMessageDTO() {
    }

    public PushMessageDTO(Long userId, PushBashDTO<?> pushBashDTO) {
        this.userId = userId;
        this.pushBashDTO = pushBashDTO;
        this.pushType = PushTypeEnum.USER.getType();
    }

    public PushMessageDTO(PushBashDTO<?> pushBashDTO) {
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
