package com.clarity.springcloud.service;

import com.clarity.springcloud.entities.CommonResult;
import com.clarity.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * PaymentFeignService 接口
 */

@FeignClient(value = "nacos-payment-provider", fallback = PaymentFeignFallbackServiceImpl.class)
public interface PaymentFeignService {

    @GetMapping("/payment/sql/{id}")
    CommonResult<Payment> paymentSql(@PathVariable("id") Long id);
}
