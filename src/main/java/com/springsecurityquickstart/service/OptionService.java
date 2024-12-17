package com.springsecurityquickstart.service;

import com.springsecurityquickstart.pojo.option.Option;
import com.springsecurityquickstart.pojo.option.OptionChildren;

import java.util.List;

public interface OptionService {
    List<Option> optionList();

    void addOption(Option option);

    void addOptionChildren(OptionChildren child);

    void deleteOption(Integer optionId);

    void updateOption(Option option);
}
