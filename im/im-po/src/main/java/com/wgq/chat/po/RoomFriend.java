package com.wgq.chat.po;

import com.sheep.protocol.POJO;

/**
 * @ClassName: RoomFriendBO
 * @Author : wgq
 * @Date :2023/9/15  10:09
 * @Description:
 * @Version :1.0
 */
public class RoomFriend implements POJO {
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
