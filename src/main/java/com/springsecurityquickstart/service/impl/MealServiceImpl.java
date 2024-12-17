package com.springsecurityquickstart.service.impl;

import com.springsecurityquickstart.mapper.MealMapper;
import com.springsecurityquickstart.pojo.meal.Meal;
import com.springsecurityquickstart.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealMapper mealMapper;

    /**
     * 查詢全部餐點
     * @return
     */
    @Override
    public List<Meal> mealList() {
        return mealMapper.mealList();
    }

    /**
     * 新增餐點
     * @param meal
     */
    @Override
    public void insert(Meal meal) {
        meal.setCreateTime(LocalDateTime.now());
        meal.setUpdateTime(LocalDateTime.now());

        mealMapper.insert(meal);
    }

    /**
     * 刪除餐點
     * @param id
     */
    @Override
    public void delete(Integer id) {
        mealMapper.deleteById(id);
    }

    /**
     * 修改餐點
     * @param meal
     */
    @Override
    public void update(Meal meal) {
        meal.setUpdateTime(LocalDateTime.now());

        mealMapper.update(meal);
    }
}
