package com.wgq.chat.contact.controller;

import com.sheep.protocol.BusinessException;
import com.wgq.chat.contact.assembler.QunAssembler;
import com.wgq.chat.contact.bo.QunBO;
import com.wgq.chat.contact.protocol.qun.InviteFriendParam;
import com.wgq.chat.contact.protocol.qun.QunCreateParam;
import com.wgq.chat.contact.protocol.qun.QunModifyParam;
import com.wgq.chat.contact.protocol.qun.RemoveMemberParam;
import com.wgq.chat.contact.service.QunService;
import com.wgq.chat.contact.vo.QunPlazaVo;
import com.wgq.chat.contact.vo.QunVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * @ClassName QunController
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/23 21:26
 * @Version 1.0
 **/
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
    @GetMapping("detail/{qunId}")
    public QunVO detail(@PathVariable("qunId")Long qunId){
        QunBO qunBO = this.qunService.detail(qunId);
        return this.qunAssembler.toQunVo(qunBO);
    }


    @ApiOperation("通过类别获取群列表")
    @GetMapping("plaza-of-category-id/{categoryId}")
    public List<QunVO> qunPlazaOfCategoryId(@PathVariable("categoryId")Long categoryId){
        return null;
    }

    @ApiOperation("群广场")
    @GetMapping("qun-plaza")
    public QunPlazaVo qunPlaza(){
        return null;
    }

    /**
     * 邀请好友
     * 生成token 对方会收到邀请链接
     * @param inviteFriendParam
     * @return
     */
    @ApiOperation("邀请好友加群")
    @PostMapping("invite-friend-join")
    public String inviteFriend(@RequestBody InviteFriendParam inviteFriendParam){

        return "token";
    }


    @ApiOperation("退出群")
    @PostMapping("exist-qun")
    public void existQun(@RequestBody Long qunId){

    }

    @ApiOperation("移除群成员")
    @PostMapping("remove-member")
    public void removeMember(@RequestBody RemoveMemberParam removeMemberParam){

    }

    @ApiOperation("群解散")
    @PostMapping("dissolve")
    public void dissolve(@RequestBody Long qunId){

    }

    @ApiOperation("转移群主")
    @PostMapping("transfer")
    public void transfer(long qunId,long newOwnerId){

    }

    @ApiOperation("根据群id获取申请详情")
    @PostMapping("get-apply-detail")
    public QunVO getApplyDetail(Long qunId){
        return new QunVO();
    }
}
