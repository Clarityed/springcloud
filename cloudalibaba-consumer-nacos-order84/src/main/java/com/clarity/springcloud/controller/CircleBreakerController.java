package com.clarity.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.clarity.springcloud.entities.CommonResult;
import com.clarity.springcloud.entities.Payment;
import com.clarity.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 熔断控制器类
 *
 * @author: clarity
 * @date: 2023年01月10日 9:56
 */

@RestController
@Slf4j
@RequestMapping("/consumer/circle/breaker")
public class CircleBreakerController {

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/payment/sql/fallback/{id}")
    // @SentinelResource("fallback") // 没有配置
    // @SentinelResource(value = "fallback", fallback = "handlerFallback") // 只配置 fallback，只负责业务异常
    // @SentinelResource(value = "fallback", blockHandler = "blockHandler") // blockHandler 负责在 sentinel 里面配置的降级限流
    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallback",
            exceptionsToIgnore = {IllegalArgumentException.class}) // fallback 和 blockHandler 都配置
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {

        CommonResult result = restTemplate.getForObject(serverUrl + "/payment/sql/" + id, CommonResult.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException, 非法参数异常");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException, 该 ID 没有对应记录，空指针异常");
        }
        return result;
    }

    /**
     * blockHandler 负责在 sentinel 里面配置的降级限流
     *
     * @param id 查询支付订单 id
     * @param e  异常信息
     * @return 降级返回
     */
    public CommonResult blockHandler(@PathVariable("id") Long id, BlockException e) {

        return new CommonResult(444, "blockHandler-sentinel限流, 无此流水 id: " + id + " " + e.getMessage(), null);
    }

    /**
     * handlerFallback 只负责业务异常
     *
     * @param id 查询支付订单 id
     * @param e  异常信息
     * @return 降级返回
     */
    public CommonResult handlerFallback(@PathVariable("id") Long id, Throwable e) {

        return new CommonResult(444, "兜底异常 handlerFallback，exception 内容，没有这条 id：" + id + "的数据 " + e.getMessage(), null);
    }

    @GetMapping("/payment/sql/feign/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id") Long id) {

        // 这里可以使用 sentinel 的注解来配置允许时异常，或者限流熔断热点等等配置
        if (id == 4) {
            throw new RuntimeException("没有该id");
        }
        return paymentFeignService.paymentSql(id);
    }
}
