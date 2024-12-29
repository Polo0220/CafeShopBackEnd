package com.springsecurityquickstart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springsecurityquickstart.domain.User;
import com.springsecurityquickstart.mapper.UserMapper;
import com.springsecurityquickstart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUser(User user) {
        // 檢查用戶是否存在
        User existingUser = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", user.getUserName()));
        if (existingUser != null) {
            throw new RuntimeException("用戶名已存在");
        }

        // 加密密碼
        String encryptedPassword = passwordEncoder.encode(user.getPassword());

        // 設置加密後的密碼
        user.setPassword(encryptedPassword);

        // 保存用戶
        userMapper.insert(user);

        user.setStatus("0");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
    }
}
