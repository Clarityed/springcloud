package com.clarity.springcloud.service;

import com.clarity.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 库存服务接口
 *
 * @author: clarity
 * @date: 2023年01月11日 11:01
 */

@FeignClient(value = "seata-storage-service")
public interface StorageService {

    /**
     * 扣除产品库存
     * @param productId 产品 id
     * @param count 扣除数量
     * @return 通用返回类，是否成功
     */
    @PostMapping("/storage/decreaseProductStorage")
    CommonResult decreaseProductStorage(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
