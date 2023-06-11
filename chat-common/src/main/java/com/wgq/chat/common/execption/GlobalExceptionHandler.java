package com.wgq.chat.common.execption;


import com.sheep.protocol.BusinessException;
import com.sheep.protocol.Result;
import com.wgq.chat.contact.protocol.constant.BusinessCodeEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(BusinessException.class)
    public Result<Object> BusinessExceptionHandler(BusinessException e){
//        log.error("异常类型:{},业务信息:{},原因:{}", e.getClass(),e.toString(),e);
        return Result.fail(e.getCode(),e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public Result<Object> ExceptionHandler(Exception e){
//        log.error("异常类型:{},原因:{}", e.getClass(),e);
        return Result.fail(BusinessCodeEnum.FAILED);
    }
}
