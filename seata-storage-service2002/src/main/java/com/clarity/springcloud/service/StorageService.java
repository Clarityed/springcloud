package com.clarity.springcloud.service;

import com.clarity.springcloud.domian.CommonResult;

/**
 * 库存服务接口
 *
 * @author: clarity
 * @date: 2023年01月11日 11:57
 */

public interface StorageService {

    /**
     * 扣除产品库存
     * @param productId 产品 id
     * @param count 扣除数量
     */
    void decreaseProductStorage(Long productId, Integer count);
}
