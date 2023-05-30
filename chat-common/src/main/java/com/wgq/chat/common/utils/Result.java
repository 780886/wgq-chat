package com.wgq.chat.common.utils;


import com.wgq.chat.common.enums.BusinessCodeEnum;
import com.wgq.chat.common.enums.StatusCode;
import com.wgq.chat.common.protocol.VO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


public class Result<T> implements VO {

    @ApiModelProperty("成功标识")
    private  boolean success; //是否成功
    @ApiModelProperty("返回码")
    private Integer code;
    @ApiModelProperty("返回消息")
    private  String message;
    @ApiModelProperty("返回对象")
    private  T  data ;





    public static <T> Result<T> success(){
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.TRUE);
        result.setCode(BusinessCodeEnum.SUCCESS.getCode());
        result.setMessage(BusinessCodeEnum.SUCCESS.getMsg());
        result.setData(null);
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.TRUE);
        result.setCode(BusinessCodeEnum.SUCCESS.getCode());
        result.setMessage(BusinessCodeEnum.SUCCESS.getMsg());
        result.setData(data);

        return result;
    }
    public static <T> Result<T> fail(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.FALSE);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> fail(StatusCode statusCode) {
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.FALSE);
        result.setCode(statusCode.getCode());
        result.setMessage(statusCode.getMsg());
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
