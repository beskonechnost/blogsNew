package com.blogs.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Log4j
public class CustomAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void afterPropertiesSet () throws Exception {
        setRealmName("Real Name");
        super.afterPropertiesSet();
    }

    @Override
    public void commence (HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        PrintWriter writer = response.getWriter();
        Map<String, Object> map = new HashMap<>(2);
        map.put("statusCode", HttpServletResponse.SC_FORBIDDEN);
        String errorMessage = authEx.getMessage();
        map.put("message", errorMessage);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, map);
        log.error(authEx.getMessage());
    }

}
