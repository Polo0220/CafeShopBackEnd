package com.springsecurityquickstart.service;


import com.springsecurityquickstart.pojo.option.OptionChildren;

public interface OptionChildrenService {

    void deleteOptionChildren(Integer childId);

    void updateOptionChildren(OptionChildren child);
}
