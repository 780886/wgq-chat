package com.wgq.chat.protocol.dto;

import com.sheep.protocol.DTO;
import com.wgq.chat.protocol.enums.RequestTypeEnum;

/**
 * @ClassName WebsocketBaseRequtstDTO
 * @Description TODO
 * @Author wgq
 * @Date 2023/9/7 10:56
 * @Version 1.0
 **/
public class WebsocketRequestBaseDTO implements DTO {

    /**
     * @see RequestTypeEnum
     */
    private Integer type;

    /**
     * 每个请求包具体的数据，类型不同结果不同
     */
    private String token;

    public WebsocketRequestBaseDTO() {
    }

    public WebsocketRequestBaseDTO(Integer type, String token) {
        this.type = type;
        this.token = token;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
