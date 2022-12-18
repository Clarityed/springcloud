package com.clarity.springcloud.dao;

import com.clarity.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    /**
     * 创建支付记录
     * @param payment 支付信息对象
     * @return 成功 - 1 失败 - 0
     */
    int createPayment(Payment payment);

    /**
     * 按 id 查询，单条记录
     * @param id 支付记录 id
     * @return 支付对象信息
     */
    Payment getPaymentById(@Param("id") Long id);
}
