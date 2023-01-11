package com.clarity.springcloud.dao;

import com.clarity.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * dao 层（关联 mybatis xml 文件）
 *
 * @author: clarity
 * @date: 2023年01月11日 10:32
 */

@Mapper
public interface OrderDao {

    /**
     * 创建订单
     * @param order 订单
     */
    void createOrder(Order order);

    /**
     * 更新用户订单状态为已完成
     * @param userId 用户 id
     * @param state 订单状态
     */
    void updateOrderState(@Param("userId") Long userId, @Param("state") Integer state);
}
