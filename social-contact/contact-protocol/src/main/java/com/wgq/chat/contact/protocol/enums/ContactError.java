package com.wgq.chat.contact.protocol.enums;

import com.sheep.protocol.ErrorSupport;
import com.sheep.protocol.ModuleSupport;
import com.wgq.chat.contact.protocol.constant.ContactModule;

public enum ContactError implements ErrorSupport {

    USER_IDENTIFY_INFO_EMPTY(false,ContactModule.CONTACT,"00","user information is empty"),
    USER_IDENTIFY_INFO_ID_IS_EMPTY(false,ContactModule.CONTACT,"01","user id is empty"),
    USER_SECRET_IDENTIFY_IS_EMPTY(false,ContactModule.CONTACT,"02","user secret identify is empty"),
    AUDIT_BUSINESS_TYPE_NOT_MATCH(false,ContactModule.CONTACT,"03", "audit business type is not match"),
    AUDIT_USER_IS_NOT_MATCH(false,ContactModule.CONTACT,"04", "audit user id not match"),
    AUDIT_NOT_EXIST(false, ContactModule.CONTACT, "05", "audit record not exist"),
    AGREED_FRIEND_APPLY(false, ContactModule.CONTACT, "06", "agreed friend apply"),


    QUN_NAME_IS_EMPTY(false, ContactModule.QUN,"01","qun name is empty"),
    QUN_CATEGORY_IS_EMPTY(false,ContactModule.QUN,"02","qun category is empty"),
    QUN_ID_IS_EMPTY(false, ContactModule.QUN,"03","qun id is empty"),
    QUN_NOT_FOUND(false, ContactModule.QUN, "04", "qun can't be found!"),
    NATIONALITY_OF_QUN_EMPTY(false, ContactModule.QUN, "05", "qun nationality can't be empty"),
    QUN_STATUS_INVALID(false, ContactModule.QUN, "06", "status of qun is invalid!"),
    CATEGORY_OF_QUN_EMPTY(false, ContactModule.QUN, "07", "qun category can't be empty"),
    QUN_OWNER_IS_NOT_MATCH(false, ContactModule.QUN, "08", "qun owner can't match"),
    USER_IS_MEMBER(false, ContactModule.QUN, "09", "user is member of qun"),
    USER_IS_NOT_MEMBER(false, ContactModule.QUN, "10", "user is not member of qun"),

    INVITE_TOKEN_IS_EMPTY(false, ContactModule.QUN, "11",  "invite token is empty");

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
