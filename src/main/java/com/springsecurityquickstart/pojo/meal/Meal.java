package com.springsecurityquickstart.pojo.meal;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.springsecurityquickstart.pojo.option.Option;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 餐點實體類
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meal {
    private Integer mealId; //ID
    private String name; //名稱
    private Integer price; //價格
    private Integer count; //數量
    private String description; // 描述
    private String image; // 圖片
    private Integer themeId; // 主題ID
    private LocalDateTime createTime; //創建時間
    private LocalDateTime updateTime; //更新時間
    private List<Option> options = new ArrayList<>();
}
