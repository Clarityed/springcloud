package com.clarity.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.clarity.springcloud.entities.CommonResult;
import com.clarity.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 熔断控制器类
 *
 * @author: clarity
 * @date: 2023年01月10日 9:56
 */

@RestController
@Slf4j
@RequestMapping("/consumer/circle/breaker")
public class CircleBreakerController {

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/sql/fallback/{id}")
    @SentinelResource("fallback")
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        CommonResult result = restTemplate.getForObject(serverUrl + "/payment/sql/" + id, CommonResult.class, id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException, 非法参数异常");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException, 该 ID 没有对应记录，空指针异常");
        }

        return result;
    }
}
