package com.qs.mvc.base.config;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.runtime.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通过@ControllerAdvice可以对于控制器的全局配置放在同一个位置，这样注解了@Conyroller的类的方法可以
 * 使用@ExceptionHandler、@InitBinder、@ModelAttribute注解到方法上，这对所有注解了@RequestMapping
 * 的控制器的方法有效；
 */
@ControllerAdvice  //声明一个控制器建言
public class ExceptionHandlerAdvice {
    private final static Logger LOG = Logger.getLogger(ExceptionHandlerAdvice.class);

    /**
     * 当请求出现异常时会执行此方法，此处定义全局处理，返回通用异常处理页面；
     * 这里的value属性可以指定可过滤拦截的条件，此处拦截所有的Exception；
     *
     * 注意：经过测试发现，只有在controller中get请求时才会跳转页面，post请求不会；
     */
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView exception(Exception exception, WebRequest request) {
//        ModelAndView modelAndView = new ModelAndView("error");  //定义error页面
//        modelAndView.addObject("errorMessage", exception);
//        LOG.error(exception);
//        return modelAndView;
//    }

    /**
     * @ModelAttribute 注解将键值对添加到全局，所有注解@RequestMapping的方法（执行这个方法前）都会获得
     * 此键值对；（备注：假如需要在每个请求中都带上某个指定的参数，用来标识具体的业务，可以使用这种方式实现）
     */
    @ModelAttribute
    public void addAttribute(Model model){
        model.addAttribute("msg","这个是额外信息");
    }

    /**
     * @InitBinder 在请求到达指定方法之前，绑定请求中参数，之后在执行@RequestMapping请求；
     * 可以将请求参数中日期装换为指定格式Date类型时间；
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
}
