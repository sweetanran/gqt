package com.cheer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cheer.entity.MallProductAttrEntity;

import java.util.List;

/**
 * 商品属性表
 *
 * @author cheer
 */
public interface MallProductAttrService extends IService<MallProductAttrEntity> {

    /**
     * 删除商品规格属性
     */
    void deleteByProductId(Long productId);

    /**
     * 删除商品规格属性
     */
    void deleteByProductIds(List<Long> productIds);

    /**
     * 查询商品规格属性
     */
    List<MallProductAttrEntity> listByProductId(Long productId);

}

