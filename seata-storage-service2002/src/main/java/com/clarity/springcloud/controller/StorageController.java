package com.clarity.springcloud.controller;

import com.clarity.springcloud.domian.CommonResult;
import com.clarity.springcloud.service.StorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 库存控制器
 *
 * @author: clarity
 * @date: 2023年01月11日 12:03
 */

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Resource
    private StorageService storageService;

    @PostMapping("/decreaseProductStorage")
    public CommonResult decreaseProductStorage(Long productId, Integer count) {
        storageService.decreaseProductStorage(productId, count);
        return new CommonResult(200, "扣减库存成功！！！");
    }
}
