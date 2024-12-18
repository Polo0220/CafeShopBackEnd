package com.springsecurityquickstart.service.impl;

import com.springsecurityquickstart.mapper.MealMapper;
import com.springsecurityquickstart.mapper.MenuMapper;
import com.springsecurityquickstart.pojo.Menu;
import com.springsecurityquickstart.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MealMapper mealMapper;

    /**
     * 查詢全部主題
     * @return
     */
    @Override
    public List<Menu> menuList() {
        return menuMapper.menuList();
    }

    /**
     * 新增主題
     * @param menu
     */
    @Override
    public void insert(Menu menu) {
        menu.setCreateTime(LocalDateTime.now());
        menu.setUpdateTime(LocalDateTime.now());

        menuMapper.insert(menu);
    }

    /**
     * 刪除主題
     * @param id
     */
    @Transactional(rollbackFor = Exception.class) //spring 事務管理
    @Override
    public void delete(Integer id) {
        // 刪除主題
        menuMapper.deleteById(id);
        // 刪除該主題下的餐點
        mealMapper.deleteByMenuId(id);
    }

    /**
     * 修改主題
     * @param menu
     */
    @Override
    public void update(Menu menu) {
        menu.setUpdateTime(LocalDateTime.now());

        menuMapper.update(menu);
    }
}
