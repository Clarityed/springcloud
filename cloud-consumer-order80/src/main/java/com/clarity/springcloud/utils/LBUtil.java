package com.clarity.springcloud.utils;

import com.clarity.springcloud.loadbalancing.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * 手写轮询算法，使用快捷工具
 *
 * @author: clarity
 * @date: 2022年12月21日 11:10
 */

@Component
public class LBUtil {

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;

    public URI useMyLB(String serviceName) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.serviceInstance(instances);
        URI uri = serviceInstance.getUri();
        if (uri == null) {
            return null;
        }
        return uri;
    }
}
