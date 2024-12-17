package com.springsecurityquickstart.pojo.meal;

import com.springsecurityquickstart.pojo.option.OptionRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealRequest {
    private Integer mealId;
    private Integer count;
    private Double price;
    private List<OptionRequest> options;
}
