package com.wgq.chat.protocol.dto;

import com.sheep.protocol.DTO;

/**
 * @ClassName: RequestLoginDTO
 * @Author : wgq
 * @Date :2023/8/22  15:51
 * @Description:
 * @Version :1.0
 */
public class PushBashDTO<T> implements DTO {

    /**
     * @see
     */
    private Integer type;

    /**
     * 每个请求包具体的数据，类型不同结果不同
     */
    private T data;

    public PushBashDTO() {
    }

    public PushBashDTO(Integer type, T data) {
        this.type = type;
        this.data = data;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
