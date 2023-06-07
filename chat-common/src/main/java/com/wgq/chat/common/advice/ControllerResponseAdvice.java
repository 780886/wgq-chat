package com.wgq.chat.common.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sheep.protocol.BusinessException;
import com.sheep.protocol.Result;
import com.wgq.chat.contact.protocol.constant.BusinessCodeEnum;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice("com.wgq.chat")
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return !methodParameter.getParameterType().isAssignableFrom(Result.class) ||
                methodParameter.hasMethodAnnotation(NotControllerResponseAdvice.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(methodParameter.getGenericParameterType().equals(String.class)){
            ObjectMapper objectMapper=  new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(Result.success(data));
            }catch (Exception e) {
                try {
                    throw new BusinessException(BusinessCodeEnum.RESPONSE_PACK_ERROR);
                } catch (BusinessException businessException) {
                    businessException.printStackTrace();
                }
            }
        }
        if (methodParameter.getGenericParameterType().equals(boolean.class)){
            boolean result = (boolean) data;
            return result?Result.success():Result.fail(BusinessCodeEnum.FAILED);
        }
        return Result.success(data);
    }
}
