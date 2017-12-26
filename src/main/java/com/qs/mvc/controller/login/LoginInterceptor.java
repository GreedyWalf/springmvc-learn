package com.qs.mvc.controller.login;

import com.qs.mvc.util.RequestContextFactory;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Resource(name = "sessionRedisTemplate")
    private StringRedisTemplate sessionRedisTemplate;


    //拦截所有的请求，在请求之前，验证用户的登录状态
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //注意request.getSession(false)之间的区别，默认参数为true，没有session会新建一个session对象
        HttpSession session = request.getSession();
        //所有非登录请求Session失效时长为1分钟
        session.setMaxInactiveInterval(60);

        String sessionId = RequestContextFactory.getCookieValue(request, "session_id");
        boolean hasSessionId = StringUtils.isBlank(sessionId) ? false : sessionRedisTemplate.hasKey(sessionId);
        //是否有登录信息
        if (BooleanUtils.isFalse(hasSessionId)) {
            RequestContextFactory.redirectLogin(request, response);
            return false;
        }

        return !setSession(request, response, sessionRedisTemplate, sessionId);
    }

    private boolean setSession(HttpServletRequest request, HttpServletResponse response, StringRedisTemplate sessionRedisTemplate, String sessionId) throws IOException {
        //直接存放Context信息，必要的取出来单独放
        if (StringUtils.isBlank(sessionId)) {
            RequestContextFactory.redirectLogin(request, response);
            return true;
        }

        String sessionKey = "LOGIN_123";
        String aSessionId = (String) sessionRedisTemplate.opsForValue().get(sessionKey);
        if (!sessionId.equalsIgnoreCase(aSessionId)) {
            RequestContextFactory.redirectLogin(request, response);
            return true;
        }

        //重置session
        sessionRedisTemplate.expire(sessionKey, 30, TimeUnit.MINUTES);
        sessionRedisTemplate.expire(sessionId, 30, TimeUnit.MINUTES);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
