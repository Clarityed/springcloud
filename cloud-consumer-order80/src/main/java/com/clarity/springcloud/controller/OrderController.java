package com.clarity.springcloud.controller;

import com.clarity.springcloud.entities.CommonResult;
import com.clarity.springcloud.entities.Payment;
import com.clarity.springcloud.utils.LBUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;

/**
 * 订单控制层
 *
 * @author: clarity
 * @date: 2022年12月17日 10:07
 */

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    public static final String PAYMENT_NAME = "CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LBUtil lbUtil = new LBUtil();

    @GetMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        if (payment == null) {
            return new CommonResult(500, "插入记录不能为空", null);
        }
        URI uri = lbUtil.useMyLB(PAYMENT_NAME);
        return restTemplate.postForObject(uri + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/payment/createEntity")
    public CommonResult<Payment> createEntity(Payment payment) {
        if (payment == null) {
            return new CommonResult(500, "插入记录不能为空", null);
        }
        URI uri = lbUtil.useMyLB(PAYMENT_NAME);
        ResponseEntity<CommonResult> commonResultResponseEntity = restTemplate.postForEntity(uri + "/payment/create", payment, CommonResult.class);
        if (commonResultResponseEntity.getStatusCode().is2xxSuccessful()) {
            log.info("请求对象信息：" + commonResultResponseEntity.getStatusCode() + commonResultResponseEntity.getHeaders());
            return commonResultResponseEntity.getBody();
        }

        return new CommonResult<>(500, "请求返回发生错误", null);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable Long id) {
        if (id <= 0) {
            return new CommonResult(500, "该数据不可能存在", null);
        }
        URI uri = lbUtil.useMyLB(PAYMENT_NAME);
        return restTemplate.getForObject(uri + "/payment/get/" + id, CommonResult.class, id);
    }

    @GetMapping("/payment/getEntity/{id}")
    public CommonResult<Payment> getEntity(@PathVariable Long id) {
        if (id <= 0) {
            return new CommonResult(500, "该数据不可能存在", null);
        }
        URI uri = lbUtil.useMyLB(PAYMENT_NAME);
        ResponseEntity<CommonResult> responseEntity = restTemplate.getForEntity(uri + "/payment/get/" + id, CommonResult.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            log.info("请求对象信息：" + responseEntity.getStatusCode() + responseEntity.getHeaders());
            return responseEntity.getBody();
        }

        return new CommonResult<>(500, "请求返回发生错误", null);
    }

    @GetMapping("/payment/lb")
    public String getPaymentByLB() {
        URI uri = lbUtil.useMyLB(PAYMENT_NAME);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri + "/payment/lb", String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }
        return null;
    }

    // ====================> zipkin+sleuth
    @GetMapping("/payment/zipkin")
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
        return result;
    }
}
