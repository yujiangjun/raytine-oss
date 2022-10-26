package com.raytine.exception;

import com.raytine.constants.CoreConstant;
import com.raytine.vo.Resp;
import com.raytine.vo.RespFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public Resp<Void> runtimeExceptionHandle(RuntimeException e){
        return RespFactory.createVoidResp(CoreConstant.FAIL_CODE,CoreConstant.FAIL_MSG+"."+e.getMessage());
    }
}
