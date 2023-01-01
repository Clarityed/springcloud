package com.clarity.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制层
 *
 * @author: clarity
 * @date: 2023年01月01日 16:18
 */

@RestController
@RefreshScope
@Slf4j
@RequestMapping("/config")
public class ConfigClientController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/info")
    public String configInfo() {
        log.info("-----------" + configInfo + " 服务端口：" + serverPort);
        return "serverPort: "+serverPort+"\t\n\n configInfo: "+configInfo;
    }
}
