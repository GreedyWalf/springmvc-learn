package com.qs.mvc.controller;

import com.qs.mvc.Entity.DemoObj;
import com.qs.mvc.service.DemoService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;


@RestController  //组合注解，@Controller和ResponseBody
@RequestMapping("/rest")
public class DemoRestController {

    @Resource
    private DemoService demoService;

    /**
     * 测试6：测试返回对象json字符串；请求url：http://localhost:8080/mvc/rest/getJson?id=1&name=zhangsan
     * @param obj 参数绑定到obj对象
     */
    @RequestMapping(value = "/getJson",produces = "application/json;charset=UTF-8")
    public DemoObj getJson(DemoObj obj) {
        return new DemoObj(obj.getId() + 1, obj.getName() + "yy"); //{"id":2,"name":"zhangsanyy"}
    }

    /**
     * 测试6：测试返回对象xml字符串；请求url：http://localhost:8080/mvc/rest/getXml?id=1&name=zhangsan
     * @param obj 参数绑定到obj对象
     */
    @RequestMapping(value = "/getXml",produces = "application/xml;charset=UTF-8")
    public DemoObj getxml(DemoObj obj, String msg, Date birth,HttpServletRequest request){
        return new DemoObj(obj.getId() + 1, obj.getName() + "yy"); //{"id":2,"name":"zhangsanyy"}
    }


    @RequestMapping(value = "/testAdvice",produces = "text/plain;charset=UTF-8")
    public String testControllerAdvice(String id, String name, Date birth, Model model) {
        //在ExceptionHandlerAdvice中碳钢@ModelAttribute注解添加的键值对数据存储在model中，可以用如下方式获取；（页面可以直接取到@ModelAttribute中的值）
        Map<String, Object> resultMap = model.asMap();
        String msg = resultMap.get("msg").toString();
        return "id=" + id + "; name=" + name + "; birth=" + birth.toLocaleString();
    }

    @RequestMapping(value = "/testRest",produces = "text/plain;charset=UTF-8")
    public String testRest(){
        return demoService.saySomething();
    }

}
