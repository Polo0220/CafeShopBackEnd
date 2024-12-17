package com.springsecurityquickstart.service.impl;

import com.springsecurityquickstart.mapper.OptionChildrenMapper;
import com.springsecurityquickstart.pojo.option.OptionChildren;
import com.springsecurityquickstart.service.OptionChildrenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OptionChildrenServiceImpl implements OptionChildrenService {
    @Autowired
    private OptionChildrenMapper optionChildrenMapper;

    @Override
    public void deleteOptionChildren(Integer childId) {
        optionChildrenMapper.deleteOptionChildrenById(childId);
    }

    @Override
    public void updateOptionChildren(OptionChildren child) {
        child.setUpdateTime(LocalDateTime.now());

        optionChildrenMapper.updateOptionChildren(child);
    }

}
