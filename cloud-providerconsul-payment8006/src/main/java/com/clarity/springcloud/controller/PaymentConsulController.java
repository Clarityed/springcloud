package com.clarity.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 支付服务控制层
 *
 * @author: clarity
 * @date: 2022年12月20日 9:17
 */

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentConsulController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/consul")
    public String paymentZookeeper() {
        log.info("远程请求来了");
        return "SpringCloud with consul: "+serverPort+"\t"+ UUID.randomUUID().toString();
    }
}
