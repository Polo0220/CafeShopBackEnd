package com.springsecurityquickstart.pojo.order;

import com.springsecurityquickstart.pojo.meal.Meal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 訂單實體類
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer orderId; //ID
    private LocalDate orderDate; //訂單日期
    private Integer orderStatus; //訂單狀態
    private Integer orderPrice; //訂單金額
    private Integer paymentStatus; //付款狀態
    private Integer paymentMethod; //付款方式
    private Integer tableNumber; //桌號
    private String remark; //備註
    private LocalDateTime createTime; //創建時間
    private LocalDateTime updateTime; //修改時間
    private List<Meal> meals = new ArrayList<>();
}
