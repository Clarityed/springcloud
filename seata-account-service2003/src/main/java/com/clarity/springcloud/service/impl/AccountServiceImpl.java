package com.clarity.springcloud.service.impl;

import com.clarity.springcloud.dao.AccountDao;
import com.clarity.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 账户接口实现类
 *
 * @author: clarity
 * @date: 2023年01月11日 12:33
 */

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public void decreaseUserMoney(Long userId, BigDecimal money) {
        log.info("------->account-service中扣减账户余额开始");
        // 模拟超时异常，全局事务回滚
        // 暂停几秒钟线程
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountDao.decreaseUserMoney(userId, money);
        log.info("------->account-service中扣减账户余额结束");
    }
}
