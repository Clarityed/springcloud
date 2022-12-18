package com.clarity.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * springboot 主启动类
 *
 * @author: clarity
 * @date: 2022年12月14日 15:15
 */

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient // 服务发现
public class PaymentMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }

}
