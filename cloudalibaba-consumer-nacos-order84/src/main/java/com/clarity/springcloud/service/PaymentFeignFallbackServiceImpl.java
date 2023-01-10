package com.clarity.springcloud.service;

import com.clarity.springcloud.entities.CommonResult;
import com.clarity.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * 支付模块方法降级类
 *
 * @author: clarity
 * @date: 2023年01月10日 11:53
 */

@Component
public class PaymentFeignFallbackServiceImpl implements PaymentFeignService {

    @Override
    public CommonResult<Payment> paymentSql(Long id) {

        return new CommonResult<>(444, "降级，------PaymentFeignFallbackServiceImpl", null);
    }
}
