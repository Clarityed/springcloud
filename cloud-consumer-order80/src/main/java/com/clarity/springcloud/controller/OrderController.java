package com.clarity.springcloud.controller;

import com.clarity.springcloud.entities.CommonResult;
import com.clarity.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 订单控制层
 *
 * @author: clarity
 * @date: 2022年12月17日 10:07
 */

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {

    public static final String PAYMENT_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        if (payment == null) {
            return new CommonResult(500, "插入记录不能为空", null);
        }
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> create(@PathVariable Long id) {
        if (id <= 0) {
            return new CommonResult(500, "该数据不可能存在", null);
        }
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class, id);
    }
}
