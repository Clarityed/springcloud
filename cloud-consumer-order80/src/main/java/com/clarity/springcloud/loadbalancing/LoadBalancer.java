package com.clarity.springcloud.loadbalancing;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 获取远程微服务的实例
 */
public interface LoadBalancer {

    ServiceInstance serviceInstance(List<ServiceInstance> serviceInstanceList);
}
