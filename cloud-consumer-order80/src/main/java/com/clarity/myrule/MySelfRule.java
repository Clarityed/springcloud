package com.clarity.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义负载均衡算法配置类
 *
 * @author: clarity
 * @date: 2022年12月20日 11:54
 */

@Configuration
public class MySelfRule {

    @Bean
    public IRule getMyRule() {
        return new RandomRule();
    }
}
