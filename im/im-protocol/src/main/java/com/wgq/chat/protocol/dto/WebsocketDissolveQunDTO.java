package com.wgq.chat.protocol.dto;

import com.sheep.protocol.DTO;

/**
 * @ClassName: WebsocketDissoverQunDTO
 * @Author : wgq
 * @Date :2023/11/7  10:28
 * @Description:
 * @Version :1.0
 */
public class WebsocketDissolveQunDTO implements DTO {

    private Object content;

    public WebsocketDissolveQunDTO(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
