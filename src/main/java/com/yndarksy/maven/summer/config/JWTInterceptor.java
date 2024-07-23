package com.yndarksy.maven.summer.config;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.yndarksy.maven.summer.common.MyException.UnAuthException;
import com.yndarksy.maven.summer.userserver.entity.User;
import com.yndarksy.maven.summer.userserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class JWTInterceptor implements HandlerInterceptor{
    @Autowired
    JWTConfig jwtConfig;

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        String username = jwtConfig.getUserName(authorization);
        if(ObjectUtils.isEmpty(authorization)){
            throw  new UnAuthException("无token");
        }
        Date expireTime = jwtConfig.getExpireTime(authorization);
        if(expireTime.before(new Date())){
            throw  new UnAuthException("token过时");
        }
        User user = userService.selectUser(username);
        if(ObjectUtils.isEmpty(user)){
            throw  new UnAuthException("无该用户");
        }
        boolean verify = jwtConfig.verify(authorization, username, user.getPassword());
        if(!verify){
            throw  new UnAuthException("token验证错误");
        }
        return true;
    }
}
