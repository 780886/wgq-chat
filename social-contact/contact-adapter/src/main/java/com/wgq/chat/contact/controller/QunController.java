package com.wgq.chat.contact.controller;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.contact.assembler.QunAssembler;
import com.wgq.chat.contact.bo.QunDetailWrapBO;
import com.wgq.chat.contact.bo.QunPlazaBO;
import com.wgq.chat.contact.protocol.qun.*;
import com.wgq.chat.contact.service.QunService;
import com.wgq.chat.contact.vo.QunPlazaVO;
import com.wgq.chat.contact.vo.QunVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * @ClassName QunController
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 21:26
 * @Version 1.0
 **/
@Api(value = "群", tags = "IM 群管理")
@RequestMapping("qun")
@RestController
public class QunController {

    @Inject
    private QunService qunService;

    @Inject
    private QunAssembler qunAssembler;

    @ApiOperation("创建群")
    @PostMapping("create-qun")
    public Long createQun(@RequestBody QunCreateParam qunCreateParam) throws BusinessException {
        return this.qunService.createQun(qunCreateParam);
    }

    @ApiOperation("修改群")
    @PostMapping("modify")
    public void modify(@RequestBody QunModifyParam qunModifyParam) throws BusinessException {
        this.qunService.modify(qunModifyParam);
    }

    @ApiOperation("群详情")
    @GetMapping("detail/{roomId}")
    public QunVO detail(@PathVariable("roomId")Long roomId) throws BusinessException {
        QunDetailWrapBO qunDetail = this.qunService.detail(roomId);
        return this.qunAssembler.assemblerQun(qunDetail);
    }


    @ApiOperation("通过类别获取群列表")
    @GetMapping("plaza-of-category-id/{categoryId}")
    public QunPlazaVO qunPlazaOfCategoryId(@PathVariable("categoryId")Long categoryId) throws BusinessException {
        QunPlazaBO qunPlaza = this.qunService.qunPlazaOfCategoryId(categoryId);
        return this.qunAssembler.assembleQunPlaza(qunPlaza);
    }

    @ApiOperation("群广场")
    @GetMapping("qun-plaza")
    public QunPlazaVO qunPlaza() throws BusinessException {
        QunPlazaBO qunPlaza = this.qunService.qunPlaza();
        return this.qunAssembler.assembleQunPlaza(qunPlaza);
    }

    /**
     * 邀请好友
     * @param inviteFriendParam
     * @return
     */
    @ApiOperation("邀请好友加群")
    @PostMapping("invite-friend-join")
    public Long inviteFriend(@RequestBody InviteFriendParam inviteFriendParam) throws BusinessException {
        return this.qunService.inviteFriend(inviteFriendParam);
    }


    @ApiOperation("退出群")
    @PostMapping("exist-qun")
    public void existQun(@RequestBody Long roomId) throws BusinessException {
        this.qunService.existQun(roomId);
    }

    @ApiOperation("移除群成员")
    @PostMapping("remove-member")
    public void removeMember(@RequestBody RemoveMemberOfQunParam removeMemberOfQunParam) throws BusinessException {
        this.qunService.removeMember(removeMemberOfQunParam);
    }

    @ApiOperation("群解散")
    @PostMapping("dissolve")
    public void dissolve(@RequestBody Long roomId) throws BusinessException {
        this.qunService.dissolve(roomId);
    }

    @ApiOperation("转移群主")
    @PostMapping("transfer")
    public void transfer(@RequestBody TransferOwnerOfQunParam transferOwnerOfQun) throws BusinessException {
        this.qunService.transfer(transferOwnerOfQun);
    }

}
