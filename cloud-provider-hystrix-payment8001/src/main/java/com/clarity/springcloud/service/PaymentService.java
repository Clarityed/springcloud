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
}
