package com.wgq.chat.bo;

import com.sheep.protocol.BO;

import java.util.Date;

/**
 * @ClassName: RoomFriendBO
 * @Author : wgq
 * @Date :2023/9/15  10:09
 * @Description:
 * @Version :1.0
 */
public class RoomFriendBO implements BO {
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
