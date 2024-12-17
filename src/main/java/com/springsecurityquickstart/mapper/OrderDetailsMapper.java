package com.springsecurityquickstart.mapper;

import com.springsecurityquickstart.pojo.OrderDetails;
import com.springsecurityquickstart.pojo.meal.MealRequest;
import com.springsecurityquickstart.pojo.option.OptionRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface OrderDetailsMapper {
    @Insert("INSERT INTO tb_order_details (order_id, meal_id, count, price) VALUES (#{orderId}, #{mealId}, #{count}, #{price})")
    void insertOrderDetails(OrderDetails orderDetails);

    @Insert("INSERT INTO tb_order_details_option_children (order_id, meal_id, option_id, option_child_id) " +
            "VALUES (#{orderId}, #{mealId}, #{optionId}, #{optionChildId})")
    void insertOptionChildren(@Param("orderId") Integer orderId,
                              @Param("mealId") Integer mealId,
                              @Param("optionId") Integer optionId,
                              @Param("optionChildId") Integer optionChildId);

    /**
     * 根據訂單ID查詢餐點
     * @param orderId
     * @return
     */
    @Select("SELECT meal_id, count, price FROM tb_order_details WHERE order_id = #{orderId}")
    List<MealRequest> selectMealsByOrderId(@Param("orderId") Integer orderId);

    /**
     * 根據訂單ID和餐點ID查詢選項
     * @param orderId
     * @param mealId
     * @return
     */
    @Select("SELECT DISTINCT option_id FROM tb_order_details_option_children WHERE order_id = #{orderId} AND meal_id = #{mealId}")
    List<OptionRequest> selectOptionsByMealIdAndOrderId(@Param("orderId") Integer orderId, @Param("mealId") Integer mealId);

    /**
     * 根據訂單ID、餐點ID和選項ID查詢選項子選項
     * @param orderId
     * @param mealId
     * @param optionId
     * @return
     */
    @Select("SELECT DISTINCT option_child_id FROM tb_order_details_option_children WHERE order_id = #{orderId} AND meal_id = #{mealId} AND option_id = #{optionId}")
    List<Integer> selectOptionChildren(@Param("orderId") Integer orderId, @Param("mealId") Integer mealId, @Param("optionId") Integer optionId);
}