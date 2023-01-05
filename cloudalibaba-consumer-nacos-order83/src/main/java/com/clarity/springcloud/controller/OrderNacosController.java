package com.clarity.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 控制器
 *
 * @author: clarity
 * @date: 2023年01月05日 11:39
 */

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderNacosController {

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/nacos/{id}")
    public String paymentInfo(@PathVariable Integer id) {
        log.info("--------------id: " + id);
        return restTemplate.getForObject(serverUrl + "/payment/nacos/" + id, String.class);
    }
}
