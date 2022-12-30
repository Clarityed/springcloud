package com.clarity.springcloud;

import java.time.ZonedDateTime;

/**
 * 时间格式测试
 *
 * @author: clarity
 * @date: 2022年12月30日 20:02
 */
public class TimeTest {

    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime); // 2022-12-30T20:07:48.734+08:00[Asia/Shanghai]
    }
}
