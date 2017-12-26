package com.qs.mvc.base.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定义一个Bean继承HandlerInterceptorAdapter类，该类即为自定义拦截器；
 *
 *  如何使自定义的拦截器生效？如何指定多个拦截器？
 *      在MyMvcConfig类中配置拦截器Bean，之后注册拦截器，这样在任意访问一起请求时都会触发拦截器
 */
public class DemoInterceptor extends HandlerInterceptorAdapter{


    //重写preHandle方法，在请求发生前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }

    //重新postHandle方法，在请求发生后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long starTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        long endTime = System.currentTimeMillis();
        System.out.println("-->>本次请求处理时间为：" + new Long(endTime - starTime) + "ms");
        request.setAttribute("handlingTime=", endTime - starTime);
    }
}
