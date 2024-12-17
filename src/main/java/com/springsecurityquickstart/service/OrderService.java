package com.springsecurityquickstart.service;

import com.springsecurityquickstart.pojo.order.OrderRequest;

import java.util.List;

public interface OrderService {
    List<OrderRequest> getAllOrders();

    void createOrder(OrderRequest orderRequest);

    OrderRequest getOrderById(Integer orderId);

}
