package com.wgq.chat.protocol.dto;

import com.sheep.protocol.DTO;

import java.util.Date;

/**
 * <p>
 * 单聊房间表
 * </p>
 *
 * @author <a href="https://github.com/zongzibinbin">abin</a>
 * @since 2023-07-22
 */
public class RoomFriendDTO implements DTO {
    
    /**
     * id
     */
    private Long id;

    /**
     * 房间id
     */
    private Long roomId;

    /**
     * smallerUserId（更小的userId）
     */
    private Long smallerUserId;

    /**
     * largerUserId（更大的userId）
     */
    private Long largerUserId;

    /**
     * 房间key由两个uid拼接，先做排序uid1_uid2
     */
    private String roomKey;

    /**
     * 房间状态 0正常 1禁用(删好友了禁用)
     */
    private Integer status;

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

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getSmallerUserId() {
        return smallerUserId;
    }

    public void setSmallerUserId(Long smallerUserId) {
        this.smallerUserId = smallerUserId;
    }

    public Long getLargerUserId() {
        return largerUserId;
    }

    public void setLargerUserId(Long largerUserId) {
        this.largerUserId = largerUserId;
    }

    public String getRoomKey() {
        return roomKey;
    }

    public void setRoomKey(String roomKey) {
        this.roomKey = roomKey;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
