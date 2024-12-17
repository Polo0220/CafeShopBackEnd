package com.springsecurityquickstart.controller;

import com.springsecurityquickstart.anno.Log;
import com.springsecurityquickstart.pojo.Result;
import com.springsecurityquickstart.pojo.order.OrderRequest;
import com.springsecurityquickstart.service.OrderService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/orders")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 查詢所有訂單
     * @return
     */
    @GetMapping
    public Result getAllOrders() {
        List<OrderRequest> orders = orderService.getAllOrders();
        return Result.success(orders);
    }

    /**
     * 查詢單一筆訂單
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public Result getOrderById(@PathVariable Integer orderId) {
        OrderRequest orderRequest = orderService.getOrderById(orderId);
        if (orderRequest != null) {
            return Result.success(orderRequest);
        } else {
            return Result.error("查無訂單");
        }
    }

    /**
     * 新增訂單
     * @param orderRequest
     * @return
     */
    @Log
    @PostMapping
    public Result createOrder(@RequestBody OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
        return Result.success();
    }

}
