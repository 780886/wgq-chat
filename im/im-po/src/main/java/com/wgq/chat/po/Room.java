package com.wgq.chat.po;

import com.sheep.protocol.POJO;

import java.util.Date;

/**
 * @ClassName: Room
 * @Author : wgq
 * @Date :2023/9/15  11:27
 * @Description:
 * @Version :1.0
 */
public class Room implements POJO {
    /**
     * id
     */
    private Long id;

    /**
     * 房间类型 1群聊 2单聊
     *
     * @see com.abin.mallchat.common.chat.domain.enums.RoomTypeEnum
     */
    private Integer type;

    /**
     * 是否全员展示 0否 1是
     *
     * @see com.abin.mallchat.common.chat.domain.enums.HotFlagEnum
     */
    private Integer hotFlag;

    /**
     * 群最后消息的更新时间（热点群不需要写扩散，更新这里就行）
     */
    private Date activeTime;

    /**
     * 最后一条消息id
     */
    private Long lastMsgId;

    /**
     * 额外信息（根据不同类型房间有不同存储的东西）
     */
    private String extJson;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

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

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Long getLastMsgId() {
        return lastMsgId;
    }

    public void setLastMsgId(Long lastMsgId) {
        this.lastMsgId = lastMsgId;
    }

    public String getExtJson() {
        return extJson;
    }

    public void setExtJson(String extJson) {
        this.extJson = extJson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
