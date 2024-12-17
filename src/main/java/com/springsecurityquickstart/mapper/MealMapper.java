package com.springsecurityquickstart.mapper;

import com.springsecurityquickstart.pojo.meal.Meal;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * 餐點Mapper
 */
@Mapper
public interface MealMapper {
    /**
     * 查詢全部餐點數據
     * @return
     */
    @Select("select * from tb_meal")
    List<Meal> mealList();

    /**
     * 新增餐點
     * @param meal
     */
    @Insert("insert into tb_meal(name, price, count, description, image, theme_id, create_time, update_time) " +
            "values (#{name}, #{price}, #{count}, #{description}, #{image}, #{themeId}, #{createTime}, #{updateTime})")
    void insert(Meal meal);

    /**
     *根據主題ID刪除該主題中的餐點數據
     * @param themeId
     */
    @Delete("delete from tb_meal where theme_id = #{themeId}")
    void deleteByThemeId(Integer themeId);

    /**
     * 根據ID刪除餐點
     * @param id
     */
    @Delete("delete from tb_meal where meal_id = #{id}")
    void deleteById(Integer id);

    /**
     * 修改餐點(映射xml)
     * @param meal
     */
    void update(Meal meal);
}
