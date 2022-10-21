package com.spm.config;

import com.spm.common.Res;
import com.spm.common.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 * @author linyuc
 * @date 2022/1/13 17:01
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    public Res<String> handleHttpException(HttpServletRequest request, Exception e){
        log.info("发生系统异常："+e.getMessage());
        return Res.errorCodeMsg(ResponseCode.BUSINESS_ERROR.getCode(),"发生系统业务异常，异常信息："+e.getMessage());
    }
}
