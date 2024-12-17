package com.springsecurityquickstart.service;

import com.springsecurityquickstart.domain.ResponseResult;
import com.springsecurityquickstart.domain.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
