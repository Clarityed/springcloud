package com.clarity.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Http 访问远程服务
 *
 * @author: clarity
 * @date: 2023年01月05日 11:37
 */

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced // 开启负载均衡，默认轮询算法
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
