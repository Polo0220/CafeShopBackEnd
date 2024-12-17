package com.springsecurityquickstart.handler;

import com.alibaba.fastjson.JSON;
import com.springsecurityquickstart.domain.ResponseResult;
import com.springsecurityquickstart.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), "用戶認證失敗請重新查詢");
        String json = JSON.toJSONString(result);
        //處理異常
        WebUtils.renderString(response, json);
    }
}
