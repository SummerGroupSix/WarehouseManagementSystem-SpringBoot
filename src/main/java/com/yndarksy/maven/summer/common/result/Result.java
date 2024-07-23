package com.yndarksy.maven.summer.common.result;

import lombok.Data;

@Data
public class Result<T> {
    private static final Integer HTTP_OK = 200;
    private static final Integer HTTP_ERROR = 500;
    private static final Integer HTTP_UNAUTH = 401;
    private Integer code;
    private String msg;
    private T data;

    public Result<T> success(Integer code, String msg){
        this.code = code;
        this.msg = msg;
        return this;
    }
    public Result<T> success(String msg){
        this.code = HTTP_OK;
        this.msg = msg;
        return this;
    }
    public Result<T> success(){
        this.success("操作成功");
        return this;
    }
    public Result<T> error(String msg){
        this.code = HTTP_ERROR;
        this.msg = msg;
        return this;
    }
    public Result<T> error(){
        this.code = HTTP_ERROR;
        this.msg = "操作失败";
        return this;
    }
    public Result<T> unAuth(String msg){
        this.code = HTTP_UNAUTH;
        this.msg = msg;
        return this;
    }
    public Result<T> unAuth(){
        this.code = HTTP_UNAUTH;
        this.msg = "未授权";
        return this;
    }
    public Result<T> put(T data){
        this.data = data;
        return this;
    }
}
