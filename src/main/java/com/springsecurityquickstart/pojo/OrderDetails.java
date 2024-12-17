package com.springsecurityquickstart.pojo;

import com.springsecurityquickstart.pojo.option.Option;
import com.springsecurityquickstart.pojo.option.OptionChildren;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    private Integer orderId;
    private Integer mealId;
    private Integer count;
    private Double price;
    private List<Option> optionIds = new ArrayList<>();
    private List<OptionChildren> optionChildrenIds = new ArrayList<>();
}
