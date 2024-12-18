package com.springsecurityquickstart.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 種類實體類
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private Integer menuId; //ID
    private String name; //名稱
    private String image; // 圖片
    private String description; //描述
    private Boolean state; //狀態
    private LocalDateTime createTime; //創建時間
    private LocalDateTime updateTime; //更新時間
}
