package com.springsecurityquickstart.service;


import com.springsecurityquickstart.pojo.option.OptionChildren;

import java.util.List;

public interface OptionChildrenService {

    void deleteOptionChildren(Integer childId);

    void updateOptionChildren(OptionChildren child);

    List<OptionChildren> getOptionChildrenByOptionId(Integer optionId);
}
