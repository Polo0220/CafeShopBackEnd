package com.springsecurityquickstart.service.impl;


import com.springsecurityquickstart.mapper.OptionChildrenMapper;
import com.springsecurityquickstart.mapper.OptionMapper;
import com.springsecurityquickstart.pojo.option.Option;
import com.springsecurityquickstart.pojo.option.OptionChildren;
import com.springsecurityquickstart.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionMapper optionMapper;
    @Autowired
    private OptionChildrenMapper optionChildrenMapper;

    /**
     * 查詢所有option(包含optionChildren)
     * @return
     */
    @Override
    public List<Option> optionList() {
        List<Option> options = optionMapper.optionList();
        for (Option option : options) {
            List<OptionChildren> children = optionMapper.selectOptionChildrenByOptionId(option.getOptionId());
            option.setOptionChildren(children);
        }
        return options;
    }

    /**
     * 當Option不存在，則調用addOption
     * @param option
     */
    @Override
    public void addOption(Option option) {
        option.setCreateTime(LocalDateTime.now());
        option.setUpdateTime(LocalDateTime.now());

        optionMapper.insertOption(option);

        // 檢查是否有 OptionChildren，並新增每一個子選項
        List<OptionChildren> childrenList = option.getOptionChildren();
        if (childrenList != null && !childrenList.isEmpty()) {
            for (OptionChildren child : childrenList) {
                child.setOptionId(option.getOptionId()); // 設定外鍵關聯
                child.setCreateTime(LocalDateTime.now());
                child.setUpdateTime(LocalDateTime.now());

                optionChildrenMapper.insertOptionChildren(child);
            }
        }
    }

    /**
     * 當Option已經存在，則直接調用addOptionChildren
     * @param child
     */
    @Override
    public void addOptionChildren(OptionChildren child) {
        child.setCreateTime(LocalDateTime.now());
        child.setUpdateTime(LocalDateTime.now());

        optionChildrenMapper.insertOptionChildren(child);
    }

    /**
     * 刪除自訂項目(連同子選項一併刪除)
     * @param optionId
     */
    @Override
    public void deleteOption(Integer optionId) {
        optionMapper.deleteById(optionId);
        optionChildrenMapper.deleteByOptionId(optionId);
    }

    /**
     * 修改自訂項目
     * @param option
     */
    @Override
    public void updateOption(Option option) {
        option.setUpdateTime(LocalDateTime.now());

        optionMapper.updateOption(option);
    }

}
