package com.wj.springboot.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/*控制器通知类*/
@ControllerAdvice
public class MyExceptionHandler {

    /*json*/
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Map<String,Object> handlerException(Exception e) {

        HashMap<String, Object> map = new HashMap<>();

        map.put("massage",e.getMessage());
        map.put("code",888);
        return map;
    }
}
