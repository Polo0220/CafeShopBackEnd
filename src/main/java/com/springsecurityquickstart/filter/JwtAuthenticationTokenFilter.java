package com.springsecurityquickstart.filter;

import com.alibaba.fastjson.JSON;
import com.springsecurityquickstart.domain.LoginUser;
import com.springsecurityquickstart.utils.JwtUtil;
import com.springsecurityquickstart.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //獲取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //放行 (後面還有其他filter會攔截)
            filterChain.doFilter(request, response);
            return;
        }

        //解析token
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }

        //從redis中獲取用戶訊息
        String redisKey = "login:" + userid;
        String loginUserJson = redisCache.getCacheObject(redisKey);
        LoginUser loginUser = JSON.parseObject(loginUserJson, LoginUser.class);
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用戶未登錄");
        }

        //存入SecurityContextHolder
        //TODO 獲取權限訊息裝到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(request, response);
    }
}
