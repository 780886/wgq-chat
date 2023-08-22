package com.wgq.chat.protocol.dto;

/**
 * @ClassName: RequestLoginDTO
 * @Author : wgq
 * @Date :2023/8/22  15:51
 * @Description:
 * @Version :1.0
 */
public class PushBashDTO<T> {

    /**
     * 请求类型 1.请求登录二维码，2心跳检测
     *
     * @see
     */
    private Integer type;

    /**
     * 每个请求包具体的数据，类型不同结果不同
     */
    private T data;

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
