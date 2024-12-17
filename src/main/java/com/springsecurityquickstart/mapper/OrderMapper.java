package com.springsecurityquickstart.mapper;

import com.springsecurityquickstart.pojo.order.Order;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 查詢所有訂單
     * @return
     */
    @Select("SELECT * FROM tb_order")
    List<Order> selectAllOrders();

    /**
     * 查詢單一筆訂單
     * @param orderId
     * @return
     */
    @Select("SELECT * FROM tb_order WHERE order_id = #{orderId}")
    Order selectOrderById(@Param("orderId") Integer orderId);

    /**
     * 新增訂單
     * @param order
     */
    @Insert("INSERT INTO tb_order (order_date, order_status, order_price, payment_status, payment_method, table_number, remark, create_time, update_time) " +
            "VALUES (#{orderDate}, #{orderStatus}, #{orderPrice}, #{paymentStatus}, #{paymentMethod}, #{tableNumber}, #{remark}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId") // 這一行讓 MyBatis 自動獲取生成的 ID
    void insertOrder(Order order);
}
