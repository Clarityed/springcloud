package com.clarity.springcloud.loadbalancing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 负载均衡算法，实现轮询负载均衡
 *
 * @author: clarity
 * @date: 2022年12月21日 10:34
 */
@Component
@Slf4j
public class MyLoadBalancing implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 使用自旋锁的方式获取判断获取当前是第几次访问，比并发效率更高
     * @return 第几次访问
     */
    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = (current >= 2147483647 ? 0 : current + 1);
        } while (!this.atomicInteger.compareAndSet(current, next));
        log.info("******第" + next + "次访问******");
        return next;
    }

    /**
     * 实现轮询算法
     * 借助上面的自旋锁算法，得出当前为第几次访问，与集群总数几台相
     * @param serviceInstanceList 集群集合
     * @return 所要请求的微服务
     */
    @Override
    public ServiceInstance serviceInstance(List<ServiceInstance> serviceInstanceList) {
        int index = this.getAndIncrement() % serviceInstanceList.size();
        return serviceInstanceList.get(index);
    }
}
