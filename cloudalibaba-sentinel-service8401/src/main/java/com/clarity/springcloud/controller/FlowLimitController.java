package com.clarity.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        log.info("✔");
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info("✔");
        return "------testB";
    }
}
