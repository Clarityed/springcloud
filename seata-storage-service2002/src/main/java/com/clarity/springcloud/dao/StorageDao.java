package com.clarity.springcloud.dao;

import com.clarity.springcloud.domian.CommonResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * dao 层（关联 mybatis xml 文件）
 *
 * @author: clarity
 * @date: 2023年01月11日 11:45
 */

@Mapper
public interface StorageDao {

    /**
     * 扣除产品库存
     * @param productId 产品 id
     * @param count 扣除数量
     */
    void decreaseProductStorage(@Param("productId") Long productId, @Param("count") Integer count);
}
