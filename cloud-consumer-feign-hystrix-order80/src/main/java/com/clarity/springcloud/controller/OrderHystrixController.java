package com.clarity.springcloud.controller;

import com.clarity.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 控制器
 *
 * @author: clarity
 * @date: 2022年12月23日 16:03
 */

@RestController
@Slf4j
@RequestMapping("/consumer")
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("result: " + result);
        return result;
    }

    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        int  a = 100 / 0;
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        log.info("result: " + result);
        return result;
    }

    @GetMapping("/payment/hystrix/global/fallback/{id}")
    @HystrixCommand // 加了 @DefaultProperties 属性注解，并且没有写具体方法名字，就用统一全局的
    public String paymentInfo_Global_Fallback(@PathVariable("id") Integer id) {
        int  a = 100 / 0;
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        log.info("result: " + result);
        return result;
    }

    /**
     * 服务降级方法
     * @param id 任意整形数值
     * @return 返回提示消息
     */
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者 80，对方支付系统繁忙请 10 秒钟后再试或者自己运行出错请检查自己，id：" + id + "❌";
    }

    /**
     * 全局服务降级方法 fallback
     * @return 返回提示消息
     */
    public String paymentGlobalFallbackMethod() {
        return "全局异常处理信息，请稍后再试。" + "\t" + "❌";
    }
}
