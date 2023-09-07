package com.wgq.chat.protocol.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName RespTypeEnum
 * @Description TODO
 * @Author wgq
 * @Date 2023/8/22 21:14
 * @Version 1.0
 **/
public enum RespTypeEnum {

    LOGIN_AUTHORIZE_SUCCESS(1, "用户认证成功返回用户信息", null),
//    MESSAGE(4, "新消息", WSMessage.class),
//    ONLINE_OFFLINE_NOTIFY(5, "上下线通知", WSOnlineOfflineNotify.class),
    INVALIDATE_TOKEN(6, "使前端的token失效，意味着前端需要重新登录", null),
//    BLACK(7, "拉黑用户", WSBlack.class),
//    MARK(8, "消息标记", WSMsgMark.class),
//    RECALL(9, "消息撤回", WSMsgRecall.class),
//    APPLY(10, "好友申请", WSFriendApply.class),
//    MEMBER_CHANGE(11, "成员变动", WSMemberChange.class),
    ;
    private final Integer type;
    private final String desc;
    private final Class dataClass;

    private static Map<Integer, RespTypeEnum> cache;

    static {
        cache = Arrays.stream(RespTypeEnum.values()).collect(Collectors.toMap(RespTypeEnum::getType, Function.identity()));
    }

    public static RespTypeEnum of(Integer type) {
        return cache.get(type);
    }

    RespTypeEnum(Integer type, String desc, Class dataClass) {
        this.type = type;
        this.desc = desc;
        this.dataClass = dataClass;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public Class getDataClass() {
        return dataClass;
    }
}
