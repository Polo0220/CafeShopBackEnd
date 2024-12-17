package com.springsecurityquickstart.service;

import com.springsecurityquickstart.pojo.meal.Meal ;

import java.util.List;

/**
 * 餐點Service
 */
public interface MealService {
    /**
     * 查詢全部餐點數據
     * @return
     */
    List<Meal> mealList();

    /**
     * 新增餐點
     * @param meal
     */
    void insert(Meal meal);

    /**
     * 刪除餐點
     * @param id
     */
    void delete(Integer id);

    /**
     * 修改餐點
     * @param meal
     */
    void update(Meal meal);
}
