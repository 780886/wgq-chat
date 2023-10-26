package com.wgq.chat.po;

import com.sheep.protocol.POJO;
import com.sheep.protocol.dao.MethodOrder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED AUTO_INCREMENT")
    @MethodOrder(order = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @MethodOrder(order = 2)
    @Column(
            name = "type",
            columnDefinition = "int(11) UNSIGNED  DEFAULT 2 COMMENT '房间类型 1群聊 2单聊'",
            nullable = false,
            updatable = false
    )
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @MethodOrder(order = 3)
    @Column(
            name = "hot_flag",
            columnDefinition = "int(11) UNSIGNED  DEFAULT 0 COMMENT '房间类型 1群聊 2单聊'",
            nullable = false,
            updatable = false
    )
    public Integer getHotFlag() {
        return hotFlag;
    }

    public void setHotFlag(Integer hotFlag) {
        this.hotFlag = hotFlag;
    }

    @MethodOrder(order = 4)
    @Column(
            name = "last_send_time",
            columnDefinition = "bigint(11)  DEFAULT 0 COMMENT '最后消息发送时间(只有普通会话需要维护，全员会话不需要维护)'",
            nullable = false
    )
    public Long getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(Long lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    @MethodOrder(order = 5)
    @Column(
            name = "last_message_id",
            columnDefinition = "int(11) UNSIGNED  DEFAULT 0 COMMENT '会话中的最后一条消息id'",
            nullable = false,
            updatable = false
    )
    public Long getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(Long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    @MethodOrder(order = 6)
    @Column(
            name = "ext_json",
            columnDefinition = "json COMMENT '额外信息（根据不同类型房间有不同存储的东西）'",
            nullable = false,
            updatable = false
    )
    public String getExtJson() {
        return extJson;
    }

    public void setExtJson(String extJson) {
        this.extJson = extJson;
    }

    @MethodOrder(order = 7)
    @Column(
            name = "gmt_create",
            columnDefinition = "bigint(11)  DEFAULT 0 COMMENT '创建时间'",
            nullable = false,
            updatable = false
    )
    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @MethodOrder(order = 8)
    @Column(
            name = "gmt_modified",
            columnDefinition = "bigint(11)  DEFAULT 0 COMMENT '更新时间'",
            nullable = false
    )
    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}
