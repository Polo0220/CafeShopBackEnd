package com.springsecurityquickstart.service.impl;

import com.alibaba.fastjson.JSON;
import com.springsecurityquickstart.domain.LoginUser;
import com.springsecurityquickstart.domain.ResponseResult;
import com.springsecurityquickstart.domain.User;
import com.springsecurityquickstart.service.LoginService;
import com.springsecurityquickstart.utils.JwtUtil;
import com.springsecurityquickstart.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    /**
     * 登錄
     * @param user
     * @return
     */
    @Override
    public ResponseResult login(User user) {

        //AuthenticationManager authenticate進行用戶認證
        //UsernamePasswordAuthenticationFilter封裝
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        //AuthenticationManager認證
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //如果認證沒通過，給出對應的提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登錄失敗");
        }

        //如果認證通過，使用userid生成一個jwt，jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);

        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        //把完整的用戶訊息存入redis，userid作為key
        redisCache.setCacheObject("login:"+userid, JSON.toJSONString(loginUser));
        return new ResponseResult(200, "登錄成功", map);
    }

    /**
     * 登出
     * @return
     */
    @Override
    public ResponseResult logout() {
        //獲取SecurityContextHolder中的用戶id
        UsernamePasswordAuthenticationToken authentication
                = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();

        //刪除redis中的值
        redisCache.deleteObject("login:" + userid);
        return new ResponseResult(200, "登出成功");
    }
}
