package com.clarity.springcloud.controller;

import com.clarity.springcloud.entities.CommonResult;
import com.clarity.springcloud.entities.Payment;
import com.clarity.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 功能描述
 *
 * @author: scott
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
}
