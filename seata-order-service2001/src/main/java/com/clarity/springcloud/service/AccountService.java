package com.clarity.springcloud.service;

import com.clarity.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 账户服务接口
 *
 * @author: clarity
 * @date: 2023年01月11日 11:01
 */

@FeignClient(value = "seata-account-service")
public interface AccountService {

    /**
     * 扣除账户余额
     * @param userId 用户 id
     * @param money 要扣除的金额
     * @return 通用返回类，是否成功
     */
    @PostMapping("/account/decreaseUserMoney")
    CommonResult decreaseUserMoney(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
