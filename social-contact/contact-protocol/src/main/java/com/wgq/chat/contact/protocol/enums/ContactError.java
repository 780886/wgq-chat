package com.wgq.chat.contact.protocol.enums;

import com.sheep.protocol.ErrorSupport;
import com.sheep.protocol.ModuleSupport;
import com.wgq.chat.contact.protocol.constant.ContactModule;

public enum ContactError implements ErrorSupport {

    USER_IDENTIFY_INFO_EMPTY(false,ContactModule.CONTACT,"00","user information is empty"),
    USER_IDENTIFY_INFO_ID_IS_EMPTY(false,ContactModule.CONTACT,"01","user id is empty"),
    USER_SECRET_IDENTIFY_IS_EMPTY(false,ContactModule.CONTACT,"02","user secret identify is empty"),
    AUDIT_BUSINESS_TYPE(false,ContactModule.CONTACT,"03", "audit business type is not right"),
    AUDIT_USER_IS_NOT_MATCH(false,ContactModule.CONTACT,"04", "audit user id not match")
    ;


    private boolean system;
    private ModuleSupport module;
    private String code;
    private String message;

    ContactError(boolean system, ModuleSupport module, String code, String message) {
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
