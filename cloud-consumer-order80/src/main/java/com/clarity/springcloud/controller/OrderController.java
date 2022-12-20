package com.clarity.springcloud.controller;

import com.clarity.springcloud.entities.CommonResult;
import com.clarity.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        if (payment == null) {
            return new CommonResult(500, "插入记录不能为空", null);
        }
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/payment/createEntity")
    public CommonResult<Payment> createEntity(Payment payment) {
        if (payment == null) {
            return new CommonResult(500, "插入记录不能为空", null);
        }
        ResponseEntity<CommonResult> commonResultResponseEntity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if (commonResultResponseEntity.getStatusCode().is2xxSuccessful()) {
            log.info("请求对象信息：" + commonResultResponseEntity.getStatusCode() + commonResultResponseEntity.getHeaders());
            return commonResultResponseEntity.getBody();
        }

        return new CommonResult<>(500, "请求返回发生错误", null);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable Long id) {
        if (id <= 0) {
            return new CommonResult(500, "该数据不可能存在", null);
        }
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class, id);
    }

    @GetMapping("/payment/getEntity/{id}")
    public CommonResult<Payment> getEntity(@PathVariable Long id) {
        if (id <= 0) {
            return new CommonResult(500, "该数据不可能存在", null);
        }
        ResponseEntity<CommonResult> responseEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            log.info("请求对象信息：" + responseEntity.getStatusCode() + responseEntity.getHeaders());
            return responseEntity.getBody();
        }

        return new CommonResult<>(500, "请求返回发生错误", null);
    }
}
