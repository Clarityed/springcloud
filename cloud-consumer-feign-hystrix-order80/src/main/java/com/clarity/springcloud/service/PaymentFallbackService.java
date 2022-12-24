package com.clarity.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 服务降级（配置 Openfeign 使用）
 *
 * @author: clarity
 * @date: 2022年12月24日 11:28
 */

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService fallback - paymentInfo_OK" + "\t" + "❌";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "PaymentFallbackService fallback - paymentInfo_Timeout" + "\t" + "❌";
    }
}
