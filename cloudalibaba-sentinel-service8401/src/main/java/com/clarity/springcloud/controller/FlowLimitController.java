package com.clarity.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 限流控制器
 *
 * @author: clarity
 * @date: 2023年01月08日 10:30
 */

@RestController
@Slf4j
@RequestMapping("/sentinel")
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        // 测试限流：线程数
/*        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        log.info("✔");
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info("✔");
        return "------testB";
    }

    // 服务降级测试方法
    @GetMapping("/testC")
    public String testC() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getName() + "testC RT ✔");
        return "------testC";
    }

    // 服务降级测试方法
    @GetMapping("/testD")
    public String testD() {
        int age = 10 / 0;
        log.info(Thread.currentThread().getName() + "testC 异常比例 ❌");
        return "------testD";
    }

    // 服务降级测试方法
    @GetMapping("/testE")
    public String testE() {
        int age = 10 / 0;
        log.info(Thread.currentThread().getName() + "testE 异常数 ❌");
        return "------testE";
    }

    // 服务降级测试方法：热点
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "dealHandler_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        // int age = 10 / 0;
        return "------testHotKey";
    }
    public String dealHandler_testHotKey(String p1, String p2, BlockException exception) {
        return "------dealHandler_testHotKey ❌";
    }
}
