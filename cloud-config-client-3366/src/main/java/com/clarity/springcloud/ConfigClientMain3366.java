package com.clarity.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 主启动
 *
 * @author: clarity
 * @date: 2023年01月01日 16:16
 */

@SpringBootApplication
@EnableEurekaClient
public class ConfigClientMain3366 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3366.class, args);
    }
}
