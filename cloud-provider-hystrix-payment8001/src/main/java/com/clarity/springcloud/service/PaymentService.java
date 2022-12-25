package com.clarity.springcloud.service;

public interface PaymentService {

    /**
     * 正常访问，一切都 OK
     * @param id 任意整型数据
     * @return 测试信息
     */
    String paymentInfo_OK(Integer id);

    /**
     * 超时访问，演示降级
     * @param id 任意整型数据
     * @return 测试信息
     */
    String paymentInfo_Timeout(Integer id);

    /**
     * 超时访问，降级方法
     * @param id 任意整型数据
     * @return 测试信息
     */
    String paymentInfo_TimeOutHandler(Integer id);

    /**
     * 服务熔断
     * @param id 任意整型数据
     * @return 测试信息
     */
    String paymentCircuitBreaker(Integer id);

    /**
     * 服务熔断，降级方法
     * @param id 任意整型数据
     * @return 测试信息
     */
    String paymentCircuitBreaker_fallback(Integer id);
}
