package com.clarity.springcloud.service;

import java.math.BigDecimal;

/**
 * 账户服务接口
 *
 * @author: clarity
 * @date: 2023年01月11日 12:31
 */

public interface AccountService {

    /**
     * 扣除账户余额
     * @param userId 用户 id
     * @param money 要扣除的金额
     */
    void decreaseUserMoney(Long userId, BigDecimal money);
}
