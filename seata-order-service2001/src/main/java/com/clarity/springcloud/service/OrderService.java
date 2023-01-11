package com.clarity.springcloud.service;

import com.clarity.springcloud.domain.Order;

/**
 * 订单服务接口
 *
 * @author: clarity
 * @date: 2023年01月11日 10:52
 */

public interface OrderService {

    /**
     * 创建订单
     * @param order 订单
     */
    void createOrder(Order order);
}
