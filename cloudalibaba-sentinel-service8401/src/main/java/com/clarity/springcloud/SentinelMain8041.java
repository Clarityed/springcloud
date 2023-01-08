package com.clarity.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 主启动
 *
 * @author: clarity
 * @date: 2023年01月08日 10:27
 */

@SpringBootApplication
@EnableDiscoveryClient
public class SentinelMain8041 {

    public static void main(String[] args) {
        SpringApplication.run(SentinelMain8041.class, args);
    }
}
