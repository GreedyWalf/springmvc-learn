package com.qs.mvc.controller.login;

import com.qs.mvc.base.context.ExecutionContext;
import com.qs.mvc.entity.user.User;
import com.qs.mvc.util.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.HttpConstraintElement;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource(name = "commonRedisTemplate")
    private RedisTemplate commonRedisTemplate;


    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/ajaxLogin")
    @ResponseBody
    public JsonResult ajaxLogin(User user, HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();

        String userName = user.getUserName();
        String password = user.getPassword();
        HttpSession session = request.getSession(false);
         if (session == null) {
            jsonResult.setStatus(JsonStatus.ERROR);
            jsonResult.setMessage("非法登录，不知道是怎么登录的！");
            return jsonResult;
        }

        //后台生成一个sessionId，存储到cookie和redis中
        String sessionId = UUID.randomUUID().toString();

        String userId = UUID.randomUUID().toString();
          String sessionKey = "LOGIN_" + userId;

        RequestContextFactory.addCookie(response, "session_id", sessionId, -1);
        commonRedisTemplate.opsForValue().set(sessionKey, sessionId);
        //这里失效时间暂时设置为60s，调试使用
        commonRedisTemplate.expire(sessionKey, 60, TimeUnit.SECONDS);

        Map<String, String> contextMap = new HashMap<String, String>();
        contextMap.put(ExecutionContext.USER_ID, userId);
        contextMap.put(ExecutionContext.USER_NAME, userName);
        commonRedisTemplate.opsForValue().set(sessionId, contextMap);
        return jsonResult;
    }

    @RequestMapping(value = "/index")
    public String showIndex() {
        return "index";
    }

    private void addCookie(String name, String value, int maxAge, HttpServletResponse response) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(value)) {
            return;
        }

        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
