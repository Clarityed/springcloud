package com.clarity.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * 全局日志记录过滤器
 *
 * @author: clarity
 * @date: 2022年12月31日 10:09
 */

@Component
@Slf4j
public class MyLogGlobalFilter implements GlobalFilter, Ordered {

    /**
     * 真正执行过滤器效果的方法
     * @param exchange 可以获得请求的各种信息
     * @param chain 与过滤器是否通过和顺序相关
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("--------------come in MyLogGlobalFilter: " + new Date());
        String value = exchange.getRequest().getQueryParams().getFirst("clarity");
        if (value == null) {
            log.info("--------------该用户名为 null，无法登录");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 按照顺序执行过滤器
     * 一般是数值最小的优先执行
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
