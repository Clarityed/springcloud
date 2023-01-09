package com.clarity.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.clarity.springcloud.entities.CommonResult;
import com.clarity.springcloud.entities.Payment;
import com.clarity.springcloud.myhandler.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 @SentinelResource 注解控制器
 *
 * @author: clarity
 * @date: 2023年01月09日 16:13
 */

@RestController
@Slf4j
@RequestMapping("/sentinel/resource")
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "by_resource_handler")
    public CommonResult byResource() {
        return new CommonResult(200, "按资源名称限流测试 ✔", new Payment(1L, "00001"));
    }
    public CommonResult by_resource_handler(BlockException exception) {
        return new CommonResult(444, exception.getClass().getCanonicalName() + "\t 服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200, "按 Url 限流测试 ✔", new Payment(2L, "00002"));
    }

    // CustomerBlockHandler
    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handlerException2")
    public CommonResult customerBlockHandler() {
        return new CommonResult(200, "按客户自定义限流 ✔", new Payment(3L, "00003"));
    }
}
