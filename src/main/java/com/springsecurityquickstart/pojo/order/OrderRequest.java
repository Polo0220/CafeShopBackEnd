package com.springsecurityquickstart.pojo.order;

import com.springsecurityquickstart.pojo.meal.MealRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

/**
 * 訂單請求類
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private Integer orderPrice; // 訂單金額
    private Integer paymentMethod; // 付款方式
    private Integer tableNumber; // 桌號
    private String remark; // 備註
    private List<MealRequest> mealIds;
}
