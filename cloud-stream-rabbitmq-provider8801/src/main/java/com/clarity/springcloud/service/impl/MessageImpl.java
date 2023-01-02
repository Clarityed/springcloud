package com.clarity.springcloud.service.impl;

import com.clarity.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 消息生产者实现类
 *
 * @author: clarity
 * @date: 2023年01月02日 15:32
 */

// source
@EnableBinding({Source.class})
@Slf4j
public class MessageImpl implements IMessageProvider {

    /**
     * 消息的发送管道 channel
     */
    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        // Binder
        boolean result = output.send(MessageBuilder.withPayload(serial).build());
        log.info("-------------流水号：" + serial);
        return String.valueOf(result);
    }
}
