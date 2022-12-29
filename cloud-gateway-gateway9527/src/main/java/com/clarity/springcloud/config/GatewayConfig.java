/*
package com.clarity.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

*/
/**
 * 路由规则配置
 *
 * @author: clarity
 * @date: 2022年12月29日 11:25
 *//*


@Configuration
public class GatewayConfig {

    */
/**
     * 配置了一个id为route-name的路由规则，
     * 当访问地址 http://localhost:9527/guonei时会自动转发到地址：http://news.baidu.com/guonei
     * @param routeLocatorBuilder
     * @return
     *//*

    @Bean
    public RouteLocator customRouteLocator1(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("config_class_route_path_1", r -> r.path("/payment/lb").uri("http://localhost:8001/payment/lb")).build();
        return routes.build();
    }

    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("config_class_route_path_2", r -> r.path("/payment/get/**").uri("http://localhost:8001/payment/get")).build();
        return routes.build();
    }
}
*/
