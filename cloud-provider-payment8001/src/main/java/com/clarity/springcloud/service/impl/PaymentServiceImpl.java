package com.clarity.springcloud.service.impl;

import com.clarity.springcloud.dao.PaymentDao;
import com.clarity.springcloud.entities.Payment;
import com.clarity.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 功能描述
 *
 * @author: scott
 * @date: 2022年12月16日 16:16
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int createPayment(Payment payment) {
        return paymentDao.createPayment(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
