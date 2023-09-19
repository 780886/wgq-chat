package com.wgq.chat.protocol.enums;

import com.sheep.protocol.ErrorSupport;
import com.sheep.protocol.ModuleSupport;
import com.wgq.chat.protocol.constant.ImModule;

/**
 *
 *
 *
 */
public enum BusinessCodeEnum implements ErrorSupport {



    FRIEND_NUMBER_NOT_MATCH(false, ImModule.ROOM,"01", "房间创建失败，好友数量不匹配!"),
    USER_BLACK(false, ImModule.ROOM,"02", "您已经被对方拉黑!"),
    REPLY_MESSAGE_NOT_EXIST(false, ImModule.MESSAGE,"03", "回复消息不存在!"),
    ROOM_NOT_MATCH(false, ImModule.ROOM,"02", "只能回复相同会话内的消息!");


    private boolean system;
    private ModuleSupport module;
    private String code;
    private String message;

    BusinessCodeEnum(boolean system, ModuleSupport module, String code, String message) {
        this.system = system;
        this.module = module;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean system() {
        return this.system;
    }

    @Override
    public ModuleSupport module() {
        return this.module;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
