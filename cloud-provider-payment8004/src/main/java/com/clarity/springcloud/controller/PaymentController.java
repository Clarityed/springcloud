package com.clarity.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 支付控制器
 *
 * @author: clarity
 * @date: 2022年12月19日 10:11
 */

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/zk")
    public String paymentZookeeper() {
        log.info("远程请求来了");
        return "SpringCloud with zookeeper: "+serverPort+"\t"+ UUID.randomUUID().toString();
    }
}
