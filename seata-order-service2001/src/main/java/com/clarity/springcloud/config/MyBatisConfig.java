package com.clarity.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisConfig
 *
 * @author: clarity
 * @date: 2023年01月11日 11:19
 */
@Configuration
@MapperScan({"com.clarity.springcloud.dao"})
public class MyBatisConfig {
}
