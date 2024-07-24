package com.yndarksy.maven.summer.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.yndarksy.maven.summer.common.MyException.UnAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Calendar;
import java.util.Date;

@Component
public class JWTConfig {
    /**
     * 生成JWTToken
     *
     * @param userName
     * @param secret
     * @return
     */
    public String sign(String userName, String secret) {

        Algorithm algorithm = Algorithm.HMAC256(userName + secret);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 3*60*60);
        Date time = calendar.getTime();

        return JWT.create().withClaim("username", userName).withExpiresAt(time).sign(algorithm);
    }

    /**
     * 从JWTToken中获取用户名
     *
     * @param token
     * @return
     */
    public String getUserName(String token) {
        if(ObjectUtils.isEmpty(token)){
            throw new UnAuthException("token为空");
        }
        return JWT.decode(token).getClaim("username").asString();
    }

    /**
     * 从JWTToken中获取过期时间
     *
     * @param token
     * @return
     */
    public Date getExpireTime(String token) {
        if(ObjectUtils.isEmpty(token)) throw new UnAuthException("token为空");
        return JWT.decode(token).getExpiresAt();
    }

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public boolean verify(String token, String userAccount, String secret) {
        if(ObjectUtils.isEmpty(token)) throw new UnAuthException("token为空");
        try {
            Algorithm algorithm = Algorithm.HMAC256(userAccount + secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", userAccount).build();
            verifier.verify(token);
            return true;
        } catch (TokenExpiredException expiredException) {
            throw expiredException;
        } catch (Exception exception) {
            return false;
        }
    }
}
