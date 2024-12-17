package com.springsecurityquickstart.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @RequestMapping("/hello")
//    @PreAuthorize("hasAuthority('test')")
    @PreAuthorize("hasAnyAuthority('system:dept:list', 'system:test:list')")
    public String hello(){
        return "hello";
    }
}
