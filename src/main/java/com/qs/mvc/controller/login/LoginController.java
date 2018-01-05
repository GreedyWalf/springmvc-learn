package com.qs.mvc.controller.login;

import com.qs.mvc.base.context.ExecutionContext;
import com.qs.mvc.entity.user.User;
import com.qs.mvc.util.JsonResult;
import com.qs.mvc.util.JsonStatus;
import com.qs.mvc.util.RequestContextFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
    @Resource(name = "sessionRedisTemplate")
    private RedisTemplate sessionRedisTemplate;

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
        sessionRedisTemplate.opsForValue().set(sessionKey, sessionId);
        //这里失效时间暂时设置为60s，调试使用
        sessionRedisTemplate.expire(sessionKey, 60, TimeUnit.SECONDS);

        Map<String, String> contextMap = new HashMap<String, String>();
        contextMap.put(ExecutionContext.USER_ID, userId);
        contextMap.put(ExecutionContext.USER_NAME, userName);
        sessionRedisTemplate.opsForValue().set(sessionId, contextMap);
        return jsonResult;
    }

    @RequestMapping(value = "/index")
    public String showIndex() {
        return "/index/index";
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
