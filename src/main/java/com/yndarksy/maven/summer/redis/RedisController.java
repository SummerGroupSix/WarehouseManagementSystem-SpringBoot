package com.yndarksy.maven.summer.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {

    //redis客户端提供的默认模板类，只能处理字符串
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    @GetMapping("/getStr")
    public Map<String,Object> stringOpts(){
        //永久保存,需要显式删除
        stringRedisTemplate.opsForValue().set("name","张三");
        log.info("插入永久保存字符串name:张三");
        //带时间单位，指定时间删除
        stringRedisTemplate.opsForValue().set("age","18",2, TimeUnit.MINUTES);
        log.info("插入定时删除字符串age:18\n");

        //字符串获取数据
        String name = stringRedisTemplate.opsForValue().get("name");
        log.info("读取字符串name:{}",name);

        //字符串删除数据
        Boolean name1 = stringRedisTemplate.delete("name");
        log.info("删除字符串数据name:{}\n",name1);

        //获取对象
        redisTemplate.opsForValue().set("name","张三");
        log.info("通过对象插入值name:张三");

        Object name2 = redisTemplate.opsForValue().get("name");
        log.info("通过对象获取值name:{}",name2);

        //删除对象
        Boolean name3 = redisTemplate.delete("name");
        log.info("删除对象name:{}\n",name3);

        return new HashMap<>();
    }

    @GetMapping("/getList")
    public Map<String,Object> list(){
        redisTemplate.opsForList().leftPush("list","18");
        redisTemplate.opsForList().leftPush("list","19");
        redisTemplate.opsForList().leftPush("list","20");
        redisTemplate.opsForList().leftPush("list","21");

        log.info("左插入数据 list:18");
        log.info("左插入数据 list:19");
        log.info("左插入数据 list:20");
        log.info("左插入数据 list:21");
        log.info("list内存情况 21 20 19 18\n");

        //模拟栈
        log.info("模拟栈 leftPop list");
        String list = (String) redisTemplate.opsForList().leftPop("list");
        log.info("模拟栈 pop list:{}",list);
        log.info("list内存情况 20 19 18\n");

        //模拟队列
        log.info("模拟栈 rightPop list");
        String list2 = (String) redisTemplate.opsForList().rightPop("list");
        log.info("模拟栈 pop list:{}",list2);
        log.info("list内存情况 20 19");

        return new HashMap<>();

    }

    @GetMapping("/getSet")
    public Map<String,Object> Set(){
        redisTemplate.opsForSet().add("set","19");
        redisTemplate.opsForSet().add("set","20");
        redisTemplate.opsForSet().add("set","21");
        log.info("set内存情况 19 20 21");

        //模拟Set
        log.info("模拟栈set");
        String set = (String) redisTemplate.opsForSet().pop("set");
        String set2 = (String) redisTemplate.opsForSet().pop("set");
        String set3 = (String) redisTemplate.opsForSet().pop("set");
        log.info("set:{} {} {}",set,set2,set3);

        return new HashMap<>();

    }
}
