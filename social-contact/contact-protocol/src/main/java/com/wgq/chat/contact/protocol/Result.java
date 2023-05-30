package com.wgq.chat.contact.protocol;


import com.wgq.chat.contact.protocol.constant.Constant;

public class Result<T> implements VO {

    private  boolean success; //是否成功

    private String code;

    private  String message;

    private  T  data ;





    public static <T> Result<T> success(){
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.TRUE);
        result.setCode(Constant.RESULT_OK_CODE);
        result.setData(null);
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.TRUE);
        result.setCode(Constant.RESULT_OK_CODE);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(T data,String message){
        Result<T> result = new Result<>();
        result.setSuccess(Boolean.TRUE);
        result.setCode(Constant.RESULT_OK_CODE);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
    public static <T> Result<T> fail(String code, String message) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
