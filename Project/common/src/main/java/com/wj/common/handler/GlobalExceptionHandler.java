package com.wj.common.handler;

import com.wj.common.entity.Response;
import com.wj.common.entity.ResponseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
/*注解@ControllerAdvice表示这是一个控制器增强类,捕获 Controller 层抛出的异常*/
/*@RestControllerAdvice 相当于 @ControllerAdvice 与 @ResponseBody 的结合体。*/
public class GlobalExceptionHandler {

    /**
     * 接收全局异常,不会中断程序
     * @param e 全局异常
     * @return 异常信息
     */

    @ExceptionHandler(Exception.class)
    public Response handlerException(Exception e) {
        Response response = new Response();
        response.code(ResponseCode.FAIL).message(e.getMessage());
        return response;
    }

}
