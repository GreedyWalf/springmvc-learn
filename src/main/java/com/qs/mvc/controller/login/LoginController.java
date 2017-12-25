package com.qs.mvc.controller.login;

import com.qs.mvc.entity.user.User;
import com.qs.mvc.util.JsonResult;
import com.qs.mvc.util.MyErrorCode;
import com.qs.mvc.util.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.HttpConstraintElement;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequestMapping("/login")
public class LoginController {


    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/ajaxLogin")
    public JsonResult ajaxLogin(User user, HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(true);
        Assert.notNull(user, "login user is null or empty");

        HttpSession session = request.getSession(false);
        if(session == null) {
            String sessionId = UUID.randomUUID().toString();
            addCookie("session_id", sessionId, 60, response);
        }

        return jsonResult;
    }

    @RequestMapping(value = "/index")
    public String showIndex() {
        return "index";
    }

    private void addCookie(String name, String value, int maxAge, HttpServletResponse response) {
        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(value)){
            return;
        }

        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
