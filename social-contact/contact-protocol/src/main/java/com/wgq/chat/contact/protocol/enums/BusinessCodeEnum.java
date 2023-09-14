package com.wgq.chat.contact.protocol.enums;

import com.sheep.protocol.ErrorSupport;
import com.sheep.protocol.ModuleSupport;
import com.wgq.chat.contact.protocol.constant.ContactModule;

/**
 *
 *
 *
 */
public enum BusinessCodeEnum implements ErrorSupport {


//    系统
    SUCCESS(false, ContactModule.CONTACT,"200", "请求成功"),
    FAILED(false, ContactModule.CONTACT,"500", "请求失败"),
    PARAM_NOT_EMPTY(false, ContactModule.CONTACT,"1001", "参数不能为空，请重新输入!"),
    REQUEST_PARAM_INCOMPLETE(false, ContactModule.CONTACT,"1002", "请求参数不完整，请重新输入!"),
    RESPONSE_PACK_ERROR(false, ContactModule.CONTACT,"1040", "response返回包装失败!"),
    TO_MANY_REQUEST(false, ContactModule.CONTACT,"1060","请求流量过大，请稍后再试!"),
    UN_KNOW_ERROR(false, ContactModule.CONTACT,"1080","系统未知异常!"),
//    验证码
    CAPTCHA_CODE_NOT_EMPTY(false, ContactModule.CONTACT,"1101", "验证码不能为空,请重新输入验证码!"),
    CAPTCHA_CODE_NOT_EXIST(false, ContactModule.CONTACT,"1102", "验证码不存在或已过期，请重新获取验证码!"),
    CAPTCHA_CODE_ERROR(false, ContactModule.CONTACT,"1103", "验证码不正确，请重新输入!"),
    //短信验证码
    SMS_CODE_NOT_EMPTY(false, ContactModule.CONTACT,"1110", "短信验证码不能为空,请重新输入验证码!"),
    SMS_CODE_NOT_EXIST(false, ContactModule.CONTACT,"1111", "短信验证码不存在或已过期,请检查次数或重新获取验证码!"),
    SMS_CODE_VALIDATE_ERROR(false, ContactModule.CONTACT,"1112","短信验证码验证失败,请重新输入!"),
    SMS_CODE_ERROR(false, ContactModule.CONTACT,"1113", "验证码获取频率太高,稍后再试!"),
//    手机号
    PHONE_NOT_EMPTY(false, ContactModule.CONTACT,"1201", "手机号不能为空,请重新输入!"),
    PHONE_EXIST_ERROR(false, ContactModule.CONTACT,"1202", "手机号已存在,,请重新输入!"),
    PHONE_FORMAT_ERROR(false, ContactModule.CONTACT,"1203", "手机号格式不正确,请重新输入!"),
//    邮箱
    EMAIL_NOT_EMPTY(false, ContactModule.CONTACT,"1301", "邮箱不能为空,请重新输入!"),
    EMAIL_FORMAT_ERROR(false, ContactModule.CONTACT,"1302", "邮箱格式不正确，请重新输入!"),
    EMAIL_EXIST_ERROR(false, ContactModule.CONTACT,"1303", "邮箱已存在，请重新输入!"),
    //用户
    USERNAME_NOT_EMPTY(false, ContactModule.CONTACT,"1401", "用户名不能为空,请重新输入!"),
    USER_NAME_FORMAT_ERROR(false, ContactModule.CONTACT,"1402", "用户名格式不正确，请重新输入!"),
    USER_NAME_EXIST_ERROR(false, ContactModule.CONTACT,"1403", "用户名已存在，请重新输入!"),
    USER_DISABLE(false, ContactModule.CONTACT,"1404", "用户被禁用!"),
    USER_SECRET_IS_EMPTY(false, ContactModule.CONTACT,"1405", "用户密钥不能为空!"),
    //密码
    PASSWORD_NOT_EMPTY(false, ContactModule.CONTACT,"1501", "密码不能为空,请重新输入!"),
    PASSWORD_FORMAT_ERROR(false, ContactModule.CONTACT,"1502", "密码格式不正确,请重新输入!"),
    PASSWORD_NOT_EQUAL(false, ContactModule.CONTACT,"1503", "两次输入的密码不一致，请重新输入!"),
    USERNAME_PASSWORD_ERROR(false, ContactModule.CONTACT,"1504", "用户名或密码错误，请重新输入!"),
    //联系人
    EXIST_FRIEND_RELATIONSHIP(false, ContactModule.CONTACT,"1601", "你们已经是好友了!");


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
