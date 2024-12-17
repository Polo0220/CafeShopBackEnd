package com.springsecurityquickstart.aop;

import com.alibaba.fastjson.JSONObject;
import com.springsecurityquickstart.mapper.OperateLogMapper;
import com.springsecurityquickstart.pojo.OperateLog;
import com.springsecurityquickstart.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.springsecurityquickstart.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //操作人ID - 當前登錄ID (獲取請求中的jwt令牌，解析令牌)
        String jwt = request.getHeader("token");
        Claims claims = JwtUtil.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");

        //操作時間
        LocalDateTime operateTime = LocalDateTime.now();

        //操作類名
        String className = joinPoint.getTarget().getClass().getName();

        //操作方法名
        String methodName = joinPoint.getSignature().getName();

        //操作方法參數
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        //調用原始目標方法運行
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        //方法返回值
        String returnValue = JSONObject.toJSONString(result);

        //操作耗時
        long costTIme = end - begin;


        //紀錄操作日誌
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTIme);
        operateLogMapper.insert(operateLog);

        log.info("AOP紀錄操作日誌: {}", operateLog);

        return result;
    }
}
