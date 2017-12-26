package com.qs.mvc.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 测试1：使用完全注解和servlet3.0配置方式启动SpringMvc，并实现helloWorld
 */
@Controller //@Controller注解声明是一个控制器
public class HelloController {
    @Resource(name = "commonRedisTemplate")
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/testRedis")
    @ResponseBody
    public String testRedis() {
        redisTemplate.opsForValue().set("userName", "qinyupeng");
        return (String) redisTemplate.opsForValue().get("userName");
    }
}
