package com.springsecurityquickstart.controller;

import com.springsecurityquickstart.anno.Log;
import com.springsecurityquickstart.pojo.Result;
import com.springsecurityquickstart.pojo.meal.Meal;
import com.springsecurityquickstart.service.MealService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 餐點Controller
 */
@Slf4j
@RequestMapping("/meals")
@RestController
public class MealController {

    @Autowired
    private MealService mealService;

    /**
     * 查詢全部餐點數據
     * @return
     */
    @GetMapping
    public Result mealList(){
        log.info("查詢全部餐點");
        // 調用service查詢餐點數據
        List<Meal> mealList = mealService.mealList();
        return Result.success(mealList);
    }

    /**
     * 新增餐點
     * @param meal
     * @return
     */
    @Log
    @PostMapping
    public Result insert(@RequestBody Meal meal){
        log.info("新增餐點: {}", meal);
        mealService.insert(meal);
        return Result.success();
    }

    /**
     * 刪除餐點
     * @param id
     * @return
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("刪除餐點: {}", id);
        mealService.delete(id);
        return Result.success();
    }

    /**
     * 修改餐點
     * @param meal
     * @return
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Meal meal){
        log.info("修改餐點: {}", meal);
        mealService.update(meal);
        return Result.success();
    }
}
