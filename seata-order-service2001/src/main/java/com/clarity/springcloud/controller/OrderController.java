package com.clarity.springcloud.controller;

import com.clarity.springcloud.domain.CommonResult;
import com.clarity.springcloud.domain.Order;
import com.clarity.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单控制器
 *
 * @author: clarity
 * @date: 2023年01月11日 11:14
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    // 因为浏览器只能发 get 请求
    @GetMapping("/createOrder")
    public CommonResult createOrder(Order order) {
        orderService.createOrder(order);
        return new CommonResult(200, "创建订单成功！！！");
    }
}
