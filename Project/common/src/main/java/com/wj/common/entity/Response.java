package com.wj.common.entity;

import lombok.Data;

@Data
public class Response {
    //响应码
    private Integer code;
    //响应信息
    private String message;
    //响应数据
    private Object data;

    public Response code(Integer code) {
        this.code = code;
        return this;
    }

    public Response message(String message) {
        this.message = message;
        return this;
    }

    public Response data(Object data) {
        this.data = data;
        return this;
    }


}
