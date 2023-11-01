package com.wgq.chat.protocol.dto;

import com.sheep.protocol.DTO;

/**
 * @ClassName RoomDTO
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/1 14:21
 * @Version 1.0
 **/
public class RoomDTO implements DTO {

    /**
     * id
     */
    private Long id;

    /**
     * 房间类型 1群聊 2单聊
     *
     * @see com.wgq.chat.protocol.enums.RoomTypeEnum
     */
    private Integer type;

    /**
     * 是否全员展示 0否 1是
     *
     * @see com.wgq.chat.protocol.enums.HotFlagEnum
     */
    private Integer hotFlag;

    /**
     * 群最后消息的更新时间（热点群不需要写扩散，更新这里就行）
     */
    private Long lastSendTime;

    /**
     * 最后消息发送时间(只有普通会话需要维护，全员会话不需要维护)
     */
    private Long lastMessageId;

    /**
     * 额外信息（根据不同类型房间有不同存储的东西）
     */
    private String extJson;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 修改时间
     */
    private Long gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHotFlag() {
        return hotFlag;
    }

    public void setHotFlag(Integer hotFlag) {
        this.hotFlag = hotFlag;
    }

    public Long getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(Long lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public Long getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(Long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    public String getExtJson() {
        return extJson;
    }

    public void setExtJson(String extJson) {
        this.extJson = extJson;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}
