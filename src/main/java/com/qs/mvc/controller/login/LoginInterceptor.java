package com.qs.mvc.controller.login;

import com.qs.mvc.util.RequestContextFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    //拦截所有的请求，在请求之前，验证用户的登录状态
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //注意request.getSession(false)之间的区别，默认参数为true，没有session会新建一个session对象
        HttpSession session = request.getSession();
        //所有非登录请求Session失效时长为1分钟
        session.setMaxInactiveInterval(60);

        String sessionId = RequestContextFactory.getCookieValue(request, "session_id");



        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
