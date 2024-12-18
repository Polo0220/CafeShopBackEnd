package com.springsecurityquickstart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springsecurityquickstart.domain.LoginUser;
import com.springsecurityquickstart.domain.User;
import com.springsecurityquickstart.mapper.PermMapper;
import com.springsecurityquickstart.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查詢用戶訊息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        User user = userMapper.selectOne(queryWrapper);
        //如果沒有查詢到用戶就拋出異常
        if(Objects.isNull(user)){
            throw new RuntimeException("用戶名或密碼錯誤");
        }

        //TODO 查詢對應的權限訊息
//        List<String> list = new ArrayList<>(Arrays.asList("test", "admin"));
        List<String> list = menuMapper.selectPermsByUserId(user.getId());

        //把數據封裝成UserDetails返回
        return new LoginUser(user, list);
    }
}
