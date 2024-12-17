package com.springsecurityquickstart.service.impl;

import com.springsecurityquickstart.mapper.OrderDetailsMapper;
import com.springsecurityquickstart.mapper.OrderMapper;
import com.springsecurityquickstart.pojo.OrderDetails;
import com.springsecurityquickstart.pojo.meal.MealRequest;
import com.springsecurityquickstart.pojo.option.OptionRequest;
import com.springsecurityquickstart.pojo.order.Order;
import com.springsecurityquickstart.pojo.order.OrderRequest;
import com.springsecurityquickstart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    /**
     * 查詢所有訂單
     * @return
     */
    @Override
    public List<OrderRequest> getAllOrders() {
        // 查詢所有訂單基本資訊
        List<Order> orders = orderMapper.selectAllOrders();
        List<OrderRequest> orderRequests = new ArrayList<>();

        for (Order order : orders) {
            OrderRequest orderRequest = new OrderRequest();
            orderRequest.setOrderPrice(order.getOrderPrice());
            orderRequest.setPaymentMethod(order.getPaymentMethod());
            orderRequest.setTableNumber(order.getTableNumber());
            orderRequest.setRemark(order.getRemark());

            // 查詢該訂單的餐點
            List<MealRequest> mealRequests = orderDetailsMapper.selectMealsByOrderId(order.getOrderId());
            for (MealRequest mealRequest : mealRequests) {
                // 查詢該餐點的選項
                List<OptionRequest> options = orderDetailsMapper.selectOptionsByMealIdAndOrderId(order.getOrderId(), mealRequest.getMealId());

                // 查詢每個選項的選項子選項
                for (OptionRequest option : options) {
                    List<Integer> optionChildren = orderDetailsMapper.selectOptionChildren(order.getOrderId(), mealRequest.getMealId(), option.getOptionId());
                    option.setOptionChildren(optionChildren);
                }

                mealRequest.setOptions(options);
            }

            orderRequest.setMealIds(mealRequests);
            orderRequests.add(orderRequest);
        }

        return orderRequests;
    }

    /**
     * 查詢單一筆訂單
     * @param orderId
     * @return
     */
    public OrderRequest getOrderById(Integer orderId) {
        // 查詢 Order 基本資料
        Order order = orderMapper.selectOrderById(orderId);
        if (order == null) {
            return null; // 若無此訂單則返回 null
        }

        // 將查詢的結果轉換成 OrderRequest
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderPrice(order.getOrderPrice());
        orderRequest.setPaymentMethod(order.getPaymentMethod());
        orderRequest.setTableNumber(order.getTableNumber());
        orderRequest.setRemark(order.getRemark());

        // 查詢與該 Order 相關的 Meal 資料
        List<MealRequest> mealRequests = orderDetailsMapper.selectMealsByOrderId(orderId);
        for (MealRequest mealRequest : mealRequests) {
            // 查詢每個餐點的選項資料
            List<OptionRequest> options = orderDetailsMapper.selectOptionsByMealIdAndOrderId(orderId, mealRequest.getMealId());

            // 查詢每個選項的選項子選項
            for (OptionRequest option : options) {
                List<Integer> optionChildren = orderDetailsMapper.selectOptionChildren(orderId, mealRequest.getMealId(), option.getOptionId());
                option.setOptionChildren(optionChildren);
            }
            mealRequest.setOptions(options);
        }
        orderRequest.setMealIds(mealRequests);

        return orderRequest;
    }


    /**
     * 新增訂單
     * @param orderRequest
     */
    @Transactional
    public void createOrder(OrderRequest orderRequest) {
        // 建立 Order 物件並設定值
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(0);
        order.setOrderPrice(orderRequest.getOrderPrice());
        order.setPaymentStatus(0);
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setTableNumber(orderRequest.getTableNumber());
        order.setRemark(orderRequest.getRemark());
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());

        // 插入 Order 資料
        orderMapper.insertOrder(order);

        // 處理每道餐點的明細
        for (MealRequest mealRequest : orderRequest.getMealIds()) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrderId(order.getOrderId());
            orderDetails.setMealId(mealRequest.getMealId());
            orderDetails.setCount(mealRequest.getCount());
            orderDetails.setPrice(mealRequest.getPrice());

            // 插入餐點資料
            orderDetailsMapper.insertOrderDetails(orderDetails);

            // 處理每個選項和選項子選項
            for (OptionRequest optionRequest : mealRequest.getOptions()) {
                for (Integer optionChildId : optionRequest.getOptionChildren()) {
                    orderDetailsMapper.insertOptionChildren(order.getOrderId(), mealRequest.getMealId(), optionRequest.getOptionId(), optionChildId);
                }
            }
        }
    }


}
