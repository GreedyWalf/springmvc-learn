package com.qs.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试1：使用完全注解和servlet3.0配置方式启动SpringMvc，并实现helloWorld
 */
@Controller //@Controller注解声明是一个控制器
public class HelloController {

    //利用@RequestMapping配置URL和方法之间的映射 (http://localhost:8080/mvc/hello)
    @RequestMapping("/hello")
    public String hello(){
        //通过ViewResolver的Bean配置，返回值为index，说明我们的页面放置的路径为/WEB-INF/classes/views/hello.jsp
        return "hello";
    }
}
