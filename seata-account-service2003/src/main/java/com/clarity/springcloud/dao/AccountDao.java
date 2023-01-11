package com.clarity.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * dao 层（关联 mybatis xml 文件）
 *
 * @author: clarity
 * @date: 2023年01月11日 12:25
 */

@Mapper
public interface AccountDao {

    /**
     * 扣除账户余额
     * @param userId 用户 id
     * @param money 要扣除的金额
     */
    void decreaseUserMoney(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
