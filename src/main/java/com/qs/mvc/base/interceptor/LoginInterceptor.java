package com.qs.mvc.base.interceptor;

import com.qs.mvc.base.context.ExecutionContext;
import com.qs.mvc.util.RequestContextFactory;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Resource(name = "sessionRedisTemplate")
    private RedisTemplate sessionRedisTemplate;


    //拦截所有的请求，在请求之前，验证用户的登录状态（在请求之前走拦截器，返回false之后，就不会走再调用请求了）
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是登录、注册链接，不拦截
        if(request.getRequestURI().contains("/login/login") || request.getRequestURI().contains("/login/ajaxLogin")
                || request.getRequestURI().contains("/login/register") || request.getRequestURI().contains("/login/ajaxRegister")){
            return true;
        }


        //注意request.getSession(false)之间的区别，默认参数为true，没有session会新建一个session对象
        HttpSession session = request.getSession();
        //所有非登录请求Session失效时长为1分钟
        session.setMaxInactiveInterval(60);

        String sessionId = RequestContextFactory.getCookieValue(request, "session_id");
        boolean existLoginInfo = StringUtils.isBlank(sessionId) ? false : sessionRedisTemplate.hasKey(sessionId);
        if(StringUtils.isBlank(sessionId)){
            response.sendRedirect(request.getContextPath() + "/login/login");
            return false;
        }

        if(StringUtils.isNotBlank(sessionId)){
            Map<String, String> contextMap = (Map<String, String>) sessionRedisTemplate.opsForValue().get(sessionId);
            if (contextMap != null) {
                ExecutionContext.setContextMap(contextMap);
            }
        }

        //已经登录，获取用户登录信息，保存在线程变量中
        return true;
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
