package com.wgq.chat.contact.protocol.qun;

import com.sheep.protocol.Param;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName TransferOwnerOfQunParam
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 21:55
 * @Version 1.0
 **/
@ApiModel("移除群成员")
public class TransferOwnerOfQunParam implements Param {


    @ApiModelProperty("群ID")
    private Long qunId;

    @ApiModelProperty("新群主")
    private Long newOwnerId;

    public Long getQunId() {
        return qunId;
    }

    public void setQunId(Long qunId) {
        this.qunId = qunId;
    }

    public Long getNewOwnerId() {
        return newOwnerId;
    }

    public void setNewOwnerId(Long newOwnerId) {
        this.newOwnerId = newOwnerId;
    }
}
