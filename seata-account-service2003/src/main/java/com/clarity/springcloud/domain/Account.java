package com.clarity.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 账户实体
 *
 * @author: clarity
 * @date: 2023年01月11日 12:23
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    /**
     * 唯一 id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 总额度
     */
    private BigDecimal total;

    /**
     * 已用额度
     */
    private BigDecimal used;

    /**
     * 剩余额度
     */
    private BigDecimal residue;
}
