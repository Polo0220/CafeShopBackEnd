package com.springsecurityquickstart.exception;

import com.springsecurityquickstart.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局異常處理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) //捕獲所有異常
    public Result ex(Exception ex){
        ex.printStackTrace();
        return Result.error("對不起，操作失敗，請聯繫系統管理員");
    }
}
