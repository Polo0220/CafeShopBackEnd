package com.springsecurityquickstart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.springsecurityquickstart.mapper")
public class SpringSecurityQuickStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityQuickStartApplication.class, args);
	}

}
