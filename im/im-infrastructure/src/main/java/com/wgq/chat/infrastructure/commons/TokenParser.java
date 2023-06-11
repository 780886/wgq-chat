package com.wgq.chat.infrastructure.commons;

import com.sheep.core.spi.JsonFactory;
import com.sheep.enums.HttpMethod;
import com.sheep.json.Json;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.LoginUser;
import com.sheep.protocol.constant.SheepError;
import com.sheep.utils.HttpClient;


import java.util.HashMap;
import java.util.Map;

public class TokenParser {
    public static LoginUser parseUserId(String token) throws BusinessException {
        LoginUser loginUser=new LoginUser();
        if (token.contains("mock.")) {
            loginUser.setUserId(Long.valueOf(token.substring("mock.".length())));
            return loginUser;
        }
        Map<String, String> header = new HashMap<>();
        header.put("X-Sugar-Token", token);
        String result = HttpClient.request(HttpMethod.GET, "http://studyapi.zhilongsoft.com/app/authMember/info"
                , "", null, header, false);
        Json json = JsonFactory.getProvider();
        Map<String, Object> map = json.parse(result);
        Integer code = (Integer) map.get("code");
        if (code.equals(200)) {
            Map<String, Object> userMap = (Map<String, Object>) map.get("data");
            Map<String, Object> userProperty = (Map<String, Object>) userMap.get("member");
            loginUser.setUserId(Long.valueOf(userProperty.get("id").toString()));
            loginUser.setUserName(userProperty.get("name").toString());
            return loginUser;
        }
        throw new BusinessException(SheepError.USER_NOT_LOGIN);
    }
}
