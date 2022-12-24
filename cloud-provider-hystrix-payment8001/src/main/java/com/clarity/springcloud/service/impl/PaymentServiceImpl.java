package com.clarity.springcloud.service.impl;

import com.clarity.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    // 服务降级
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @Override
    public String paymentInfo_Timeout(Integer id) {
/*        int time = 5;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        int a = 10 / 0;
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_Timeout, id: " + id
                + a + "\t" + "✔";
    }

    @Override
    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " 系统繁忙或者运行错误, id: " + id
                + "\t" + "❌";
    }
}
