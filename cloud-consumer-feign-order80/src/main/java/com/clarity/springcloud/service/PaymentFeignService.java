package com.clarity.springcloud.service;

import com.clarity.springcloud.entities.CommonResult;
import com.clarity.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * 可以理解为使用 OpenFeign 映射远程调用接口
 */

@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")
@RequestMapping("/payment")
public interface PaymentFeignService {

    @PostMapping(value = "/create")
    CommonResult<Integer> createPayment(@RequestBody Payment payment);

    @GetMapping(value = "/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/lb")
    String getPaymentLB();
}
