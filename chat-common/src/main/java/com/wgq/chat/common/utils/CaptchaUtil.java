package com.wgq.chat.common.utils;

import com.wgq.chat.common.EncryptionService;
import com.wgq.chat.common.json.FastJsonExtensionJsonImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


public class CaptchaUtil {

    private static Logger logger =  LoggerFactory.getLogger(CaptchaUtil.class);


    public static String getCaptchaKey(HttpServletRequest request, EncryptionService encryptionService) {
        String ip = CommonUtils.getIpAddr(request);
        String userAgent = request.getHeader("User-Agent");
        // 相当于是把用户登陆的信息进行MD5加密后生成一个固定的常量值，
        // 作为Redis的键，把其设置设置成随机生成的字符串验证码
        String key = "account-service:captcha:" + encryptionService.encryptPassword(ip + userAgent);
        logger.info("ip:{},userAgent:{},key:{},",ip,userAgent,key);
        return key;
    }
}
