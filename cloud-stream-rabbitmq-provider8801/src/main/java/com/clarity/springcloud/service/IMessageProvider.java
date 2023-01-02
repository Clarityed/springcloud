package com.clarity.springcloud.service;


public interface IMessageProvider {

    /**
     * 发送消息的生产者
     * @return
     */
    String send();
}
