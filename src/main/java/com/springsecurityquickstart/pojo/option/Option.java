package com.springsecurityquickstart.pojo.option;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Option {
    private Integer optionId; //項目ID
    private String name; //項目名稱
    private Boolean singleChoice; //是否單選
    private String type; //項目類型
    private LocalDateTime createTime; //創建時間
    private LocalDateTime updateTime; //修改時間

    private List<OptionChildren> optionChildren;  // 子選項列表
}
