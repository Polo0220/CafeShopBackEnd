package com.springsecurityquickstart.controller;

import com.springsecurityquickstart.domain.User;
import com.springsecurityquickstart.pojo.Result;
import com.springsecurityquickstart.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Result createUser(@RequestBody User user) {
        userService.addUser(user);
        return Result.success("使用者新增成功");
    }
}
