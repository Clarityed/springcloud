package com.clarity.springcloud.controller;

import com.clarity.springcloud.entities.CommonResult;
import com.clarity.springcloud.entities.Payment;
import com.clarity.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 支付模块控制层
 *
 * @author: clarity
 * @date: 2022年12月16日 16:19
 */

@RestController
@Slf4j
@RequestMapping(value = "/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/create")
    // 先不加 @RequestBody 方便下面的测试
    public CommonResult<Payment> createPayment(@RequestBody Payment payment) {
        if (payment == null) {
            return new CommonResult(500, "插入记录不能为空", null);
        }

        int result = paymentService.createPayment(payment);

        if (result > 0) {
            return new CommonResult(200,"插入数据成功，访问的服务端口是：" + serverPort, result);
        } else {
            return new CommonResult(500, "插入数据失败", null);
        }
    }

    @GetMapping(value = "/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {

        if (id <= 0) {
            return new CommonResult(500, "该数据不可能存在", null);
        }

        Payment result = paymentService.getPaymentById(id);

        if (result != null) {
            return new CommonResult(200,"查询数据成功，访问的服务端口是：" + serverPort, result);
        } else {
            return new CommonResult(500, "查询数据失败，查询 id：" + id, null);
        }
    }

    @GetMapping(value = "/discovery")
    public Object discovery() {
        // Eureka 注册中心服务，注册的服务名称
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("微服务名称：" + service);
        }
        // 微服务应用名称为 CLOUD-PAYMENT-SERVICE 所提供的实例服务数量（实现负载均衡的相同服务数量）
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("服务信息：" + instance.getServiceId() + "\t" + instance.getHost()
                    + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/lb")
    public String getPaymentLB()
    {
        return "当前请求的微服务端口号：" + serverPort;
    }
}
