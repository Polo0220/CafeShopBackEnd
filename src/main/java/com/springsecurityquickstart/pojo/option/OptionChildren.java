package com.springsecurityquickstart.pojo.option;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionChildren {
    private Integer optionChildrenId; //選項ID
    private String name; //選項名稱
    private Boolean selected; //是否預設選中
    private Integer price; //選項價格
    private Integer optionId; //項目ID
    private LocalDateTime createTime; //創建時間
    private LocalDateTime updateTime; //修改時間
}
