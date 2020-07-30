package com.cheer.security;

import com.cheer.common.Result;
import com.cheer.common.ResultEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败时，自定义的返回结果
 *
 * @author cheer
 */
@Component
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        Result result = Result.error(ResultEnum.SERVER_ERROR.getCode(), "登录异常");
        if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
            result = Result.error(ResultEnum.SERVER_ERROR.getCode(), "用户名或密码错误");
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(objectMapper.writeValueAsString(result));
        response.getWriter().flush();
    }

}
