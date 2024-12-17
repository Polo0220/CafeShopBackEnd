package com.springsecurityquickstart.service;



import com.springsecurityquickstart.pojo.Theme;

import java.util.List;

/**
 * 主題Service
 */
public interface ThemeService {
    /**
     * 查詢全部主題數據
     * @return
     */
    List<Theme> themeList();

    /**
     * 新增主題
     * @param theme
     */
    void insert(Theme theme);

    /**
     * 刪除主題
     * @param id
     */
    void delete(Integer id);

    /**
     * 修改主題
     * @param theme
     */
    void update(Theme theme);
}
