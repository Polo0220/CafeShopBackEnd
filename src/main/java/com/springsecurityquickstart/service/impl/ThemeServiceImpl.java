package com.springsecurityquickstart.service.impl;

import com.springsecurityquickstart.mapper.MealMapper;
import com.springsecurityquickstart.mapper.ThemeMapper;
import com.springsecurityquickstart.pojo.Theme;
import com.springsecurityquickstart.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeMapper themeMapper;
    @Autowired
    private MealMapper mealMapper;

    /**
     * 查詢全部主題
     * @return
     */
    @Override
    public List<Theme> themeList() {
        return themeMapper.themeList();
    }

    /**
     * 新增主題
     * @param theme
     */
    @Override
    public void insert(Theme theme) {
        theme.setCreateTime(LocalDateTime.now());
        theme.setUpdateTime(LocalDateTime.now());

        themeMapper.insert(theme);
    }

    /**
     * 刪除主題
     * @param id
     */
    @Transactional(rollbackFor = Exception.class) //spring 事務管理
    @Override
    public void delete(Integer id) {
        // 刪除主題
        themeMapper.deleteById(id);
        // 刪除該主題下的餐點
        mealMapper.deleteByThemeId(id);
    }

    /**
     * 修改主題
     * @param theme
     */
    @Override
    public void update(Theme theme) {
        theme.setUpdateTime(LocalDateTime.now());

        themeMapper.update(theme);
    }
}
