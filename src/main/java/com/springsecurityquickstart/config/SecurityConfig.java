package com.springsecurityquickstart.config;

import com.springsecurityquickstart.filter.JwtAuthenticationTokenFilter;
import com.springsecurityquickstart.handler.AccessDeniedHandlerImpl;
import com.springsecurityquickstart.handler.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //創建BCryptPasswordEncoder注入容器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 關閉 CSRF
                .csrf().disable()
                // 不通過 Session 獲取 SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 對於登錄接口允許匿名訪問
                .antMatchers("/user/login").anonymous()
                // 對於特定方法請求允許無需權限訪問
                .antMatchers(HttpMethod.GET, "/menus").permitAll()
                .antMatchers(HttpMethod.GET, "/meals").permitAll()
                .antMatchers(HttpMethod.POST, "/orders").permitAll()
                .antMatchers(HttpMethod.GET, "/orders/{orderId}").permitAll()
                .antMatchers(HttpMethod.GET, "/optionChildren/{optionId}").permitAll()
                // 其他請求都需要身份驗證
                .anyRequest().authenticated();

        //將 jwtAuthenticationTokenFilter 加入到 UsernamePasswordAuthenticationFilter 之前
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //配置異常處理器
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        //允許跨域
        http.cors();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
