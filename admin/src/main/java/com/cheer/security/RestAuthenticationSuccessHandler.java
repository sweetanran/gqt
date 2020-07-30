package com.cheer.security;

import com.cheer.common.Result;
import com.cheer.entity.MallAdminEntity;
import com.cheer.vo.AdminVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功时，自定义的返回结果
 *
 * @author cheer
 */
@Component
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserInfoHolder userInfoHolder;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {

        MallAdminEntity adminEntity = userInfoHolder.getAdmin();
        AdminVO adminVO = new AdminVO();
        adminVO.setUsername(adminEntity.getUsername());
        adminVO.setRole(adminEntity.getRole());

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(objectMapper.writeValueAsString(Result.success(adminVO)));
        response.getWriter().flush();
    }

}
