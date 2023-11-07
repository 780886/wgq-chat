package com.wgq.chat.protocol.dto;

import com.sheep.protocol.DTO;

import java.util.Set;

/**
 * @ClassName WebsocketExistQunDTO
 * @Description TODO
 * @Author wgq
 * @Date 2023/11/7 10:04
 * @Version 1.0
 **/
public class WebsocketExistQunDTO implements DTO {

    /**
     * 推送id
     */
    private Set<Long> ids;

    public WebsocketExistQunDTO(Set<Long> ids) {
        this.ids = ids;
    }

    public Set<Long> getIds() {
        return ids;
    }

    public void setIds(Set<Long> ids) {
        this.ids = ids;
    }
}
