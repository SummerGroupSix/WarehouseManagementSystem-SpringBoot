package com.yndarksy.maven.summer.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();

        template.setConnectionFactory(redisConnectionFactory);

        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        //字符串序列化
        template.setKeySerializer(new StringRedisSerializer());
        //json序列化
        template.setValueSerializer(fastJsonRedisSerializer);

        template.setHashKeySerializer(new StringRedisSerializer());

        template.setHashValueSerializer(fastJsonRedisSerializer);
        return template;
    }
}
