package com.springsecurityquickstart.service;



import com.springsecurityquickstart.pojo.Menu;

import java.util.List;

/**
 * 主題Service
 */
public interface MenuService {
    /**
     * 查詢全部主題數據
     * @return
     */
    List<Menu> menuList();

    /**
     * 新增主題
     * @param menu
     */
    void insert(Menu menu);

    /**
     * 刪除主題
     * @param id
     */
    void delete(Integer id);

    /**
     * 修改主題
     * @param menu
     */
    void update(Menu menu);
}
