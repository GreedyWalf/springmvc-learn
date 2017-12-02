package com.qs.mvc.controller;

import com.qs.mvc.Entity.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/anno")
public class DemoAnnoController{
    /**
     * 测试访问路径
     */
    @RequestMapping("/test")
    public String test(){
        return "hello";
    }

    /**
     * 测试1：@ResponseBody返回数据（注：方法没有使用@RequestMapping注解，则默认访问路径为类路径：http://localhost:8080/mvc/anno）
     */
    @RequestMapping(produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String index(HttpServletRequest request){
        return "url:" + request.getRequestURL() + " can access";
    }


    /**
     * 测试2：绑定并获取访问路径参数，访问url：http://localhost:8080/mvc/anno/pathvar/zhangsan
     * @param str 路径参数
     */
    @RequestMapping(value = "/pathvar/{str}",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String demoPathVar(@PathVariable String str, HttpServletRequest request){
        return "url:" + request.getRequestURL() + "can access,str=" + str;
    }

    /**
     * 测试3：获取uri中的参数，访问路径：http://localhost:8080/mvc/anno/requestParam?id=1233444
     * @param id 参数
     */
    @RequestMapping(value = "/requestParam",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String passRequestParam(Long id,HttpServletRequest request){
        return "URL:" + request.getRequestURL() + " can access,id=" + id;
    }


    /**
     * 测试4：绑定请求参数在对象属性中，并获取属性值（参数值）；访问url：http://localhost:8080/mvc/anno/obj?id=1111&name=zhangsan
     * @param obj 参数绑定的对象
     */
    @RequestMapping(value = "/obj",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String passObj(DemoObj obj, HttpServletRequest request){
        return "url:" + request.getRequestURL() + " can access, obj id: " + obj.getId() + "，obj name:" + obj.getName();
    }


    /**
     * 测试5：一个方法中绑定多个路径；
     * 访问url：http://localhost:8080/mvc/anno/name1 或 http://localhost:8080/mvc/anno/name2
     */
    @RequestMapping(value ={"name1","name2"}, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String remove(HttpServletRequest request) {
        return "url:" + request.getRequestURL() + " can access";
    }
}
