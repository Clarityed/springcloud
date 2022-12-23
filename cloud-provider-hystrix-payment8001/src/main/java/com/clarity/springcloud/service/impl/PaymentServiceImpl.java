package com.clarity.springcloud.service.impl;

import com.clarity.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 测试 Hystrix 的接口实现类 （接口实现类）
 *
 * @author: clarity
 * @date: 2022年12月23日 11:09
 */

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_OK, id: " + id + "\t" + "✔";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        int time = 3;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_Timeout, id: " + id
                + " 访问耗时：" + time + "s" +  "\t" + "✔";
    }
}
