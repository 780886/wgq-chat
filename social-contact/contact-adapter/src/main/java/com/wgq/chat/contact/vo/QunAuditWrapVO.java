package com.wgq.chat.contact.vo;

import com.sheep.protocol.VO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: QunAuditWrapVO
 * @Author : wgq
 * @Date :2023/11/2  10:01
 * @Description:
 * @Version :1.0
 */
public class QunAuditWrapVO implements VO {



    /**
     * 审核状态字典
     */
    private Map<String, String> auditStatusDict = new HashMap<>();

    private List<QunAuditVO> qunAudits;

    public QunAuditWrapVO(Map<String, String> auditStatusDict, List<QunAuditVO> qunAudits) {
        this.auditStatusDict = auditStatusDict;
        this.qunAudits = qunAudits;
    }

    public Map<String, String> getAuditStatusDict() {
        return auditStatusDict;
    }

    public void setAuditStatusDict(Map<String, String> auditStatusDict) {
        this.auditStatusDict = auditStatusDict;
    }

    public List<QunAuditVO> getQunAudits() {
        return qunAudits;
    }

    public void setQunAudits(List<QunAuditVO> qunAudits) {
        this.qunAudits = qunAudits;
    }
}
