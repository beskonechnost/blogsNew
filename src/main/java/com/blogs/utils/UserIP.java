package com.blogs.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class UserIP {

    public static String getUserIP () {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        return ipAddress == null ? request.getRemoteAddr() : null;
    }

}
