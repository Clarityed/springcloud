package com.clarity.springcloud.controller;

import com.clarity.springcloud.domain.CommonResult;
import com.clarity.springcloud.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 账户控制器
 *
 * @author: clarity
 * @date: 2023年01月11日 12:35
 */

@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping("/decreaseUserMoney")
    public CommonResult decreaseUserMoney(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
        accountService.decreaseUserMoney(userId, money);
        return new CommonResult(200, "扣除账户余额成功！！！");
    }
}
