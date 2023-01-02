package com.clarity.springcloud.controller;

import com.clarity.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 控制层
 *
 * @author: clarity
 * @date: 2023年01月02日 15:42
 */

@RestController
@Slf4j
@RequestMapping("/stream/rabbit")
public class SendMessageController {

    @Resource
    private IMessageProvider iMessageProvider;

    @GetMapping("/send")
    public String sendMessage() {
        return iMessageProvider.send();
    }
}
