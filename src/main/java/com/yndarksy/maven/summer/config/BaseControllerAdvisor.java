package com.yndarksy.maven.summer.config;

import com.yndarksy.maven.summer.common.MyException.UnAuthException;
import com.yndarksy.maven.summer.common.result.Result;
import com.yndarksy.maven.summer.userserver.entity.User;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseControllerAdvisor {
    @ExceptionHandler(UnAuthException.class)
    public Result<User> UnAuthExceptionHandler(UnAuthException e){
        e.printStackTrace();
        return new Result<User>().unAuth(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<User> RuntimeExceptionHandler(RuntimeException e){
        e.printStackTrace();
        return new Result<User>().error(e.getMessage());
    }
}
