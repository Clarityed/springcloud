package com.clarity.springcloud.domian;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品库存实体类
 *
 * @author: clarity
 * @date: 2023年01月11日 11:43
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storage {

    /**
     * 唯一 id
     */
    private Long id;

    /**
     * 产品 id
     */
    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;
}
