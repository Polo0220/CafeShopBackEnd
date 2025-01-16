package com.springsecurityquickstart.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用戶表(User)實體類
 *
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 用户名
     */
    private String account;
    /**
     * 密碼
     */
    private String password;
    /**
     * 帳號狀態（0正常 1停用）
     */
    private String status;
    /**
     * 創建時間
     */
    private LocalDateTime createTime;
    /**
     * 更新時間
     */
    private LocalDateTime updateTime;
}
