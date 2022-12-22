package com.clarity.springcloud.controller;

import com.clarity.springcloud.entities.CommonResult;
import com.clarity.springcloud.entities.Payment;
import com.clarity.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单控制器
 *
 * @author: clarity
 * @date: 2022年12月22日 10:21
 */

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        if (id <= 0) {
            return new CommonResult<>(500, "不存在 id 小于 0 的记录", null);
        }
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/payment/create")
    public CommonResult<Integer> createPayment(Payment payment) {
        if (payment == null) {
            return new CommonResult<>(500, "不能添加空对象", null);
        }
        return paymentFeignService.createPayment(payment);
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String getPaymentFeignTimeout() {
        // Feign 默认等待提供者调用返回结果，默认是 1 s
        return paymentFeignService.getPaymentFeignTimeout();
    }
}
