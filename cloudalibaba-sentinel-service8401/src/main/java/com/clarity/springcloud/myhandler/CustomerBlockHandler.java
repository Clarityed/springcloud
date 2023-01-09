package com.clarity.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.clarity.springcloud.entities.CommonResult;

/**
 * 全局自定义限流处理逻辑类
 *
 * @author: clarity
 * @date: 2023年01月09日 16:55
 */

public class CustomerBlockHandler {

    public static CommonResult handlerException1(BlockException exception) {
        return new CommonResult(444, "全局自定义限流处理逻辑类, handlerException1 ❌");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(444, "全局自定义限流处理逻辑类, handlerException2 ❌");
    }
}
