package com.clarity.springcloud.service.impl;

import com.clarity.springcloud.dao.OrderDao;
import com.clarity.springcloud.domain.Order;
import com.clarity.springcloud.service.AccountService;
import com.clarity.springcloud.service.OrderService;
import com.clarity.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 订单服务实现类
 *
 * @author: clarity
 * @date: 2023年01月11日 10:54
 */

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    /**
     * 创建订单 -> 调用库存服务扣减库存 -> 调用账户服务扣减账户余额 -> 修改订单状态
     * 简单说：
     * 下订单 -> 减库存 -> 减余额 -> 改状态
     */
    @Override
    public void createOrder(Order order) {
        log.info("------->下单开始");
        // 1. 本地创建订单
        orderDao.createOrder(order);
        log.info("-------> order-service 中扣减库存开始");
        // 2. 远程调用库存服务扣减库存
        log.info("-------> order-service 中扣减库存结束");
        storageService.decreaseProductStorage(order.getProductId(), order.getCount());
        log.info("-------> order-service 中扣减余额开始");
        // 3. 远程调用账户服务扣减账户余额
        accountService.decreaseUserMoney(order.getUserId(), order.getMoney());
        log.info("-------> order-service 中扣减余额结束");
        log.info("-------> order-service 中修改订单状态开始");
        // 4. 修改订单状态，本地完成
        orderDao.updateOrderState(order.getUserId(), order.getStatus());
        log.info("-------> order-service 中修改订单状态结束");
        log.info("------->下单结束");
    }
}
