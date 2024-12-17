package com.springsecurityquickstart.pojo.option;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionRequest {
    private Integer optionId;
    private List<Integer> optionChildren;
}
