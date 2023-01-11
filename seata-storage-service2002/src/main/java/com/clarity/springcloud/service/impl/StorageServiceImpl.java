package com.clarity.springcloud.service.impl;

import com.clarity.springcloud.dao.StorageDao;
import com.clarity.springcloud.domian.CommonResult;
import com.clarity.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 库存服务接口实现
 *
 * @author: clarity
 * @date: 2023年01月11日 11:59
 */

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void decreaseProductStorage(Long productId, Integer count) {
        log.info("-------> storage-service 中扣减库存开始");
        storageDao.decreaseProductStorage(productId, count);
        log.info("-------> storage-service 中扣减库存结束");
    }
}
