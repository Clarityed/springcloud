package com.clarity.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 请求远程服务模板配置类
 *
 * @author: clarity
 * @date: 2022年12月17日 10:04
 */

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced // 使用 @LoadBalanced 注解赋予 RestTemplate 负载均衡能力
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
