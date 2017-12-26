package com.qs.mvc.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class RequestContextFactory {

    /**
     * 在cookie中获取指定名称的信息
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        if(cookie == null){
            return null;
        }

        return cookie.getValue();
    }

    //获取包含指定name的cookie对象
    private static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return null;
        }

        Cookie returnCookie = null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(name)){
                returnCookie = cookie;
                break;
            }
        }

        return returnCookie;
    }

    public static void redirectLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sourceUrl = request.getRequestURL().toString();
        if (request.getQueryString() != null) {
            sourceUrl += "?" + request.getQueryString();
        }

        response.sendRedirect(request.getContextPath() + "/login/login?returnUrl=" + URLEncoder.encode(sourceUrl, "UTF-8"));
    }
}
