package com.cheer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cheer.entity.MallProductDescEntity;

import java.util.List;

/**
 * 商品介绍表
 *
 * @author cheer
 */
public interface MallProductDescService extends IService<MallProductDescEntity> {

    /**
     * 保存商品介绍信息
     */
    void saveOrUpdateByProductId(Long productId, String desc);

    /**
     * 删除商品介绍信息
     */
    void deleteByProductId(Long productId);

    /**
     * 删除商品介绍信息
     */
    void deleteByProductIds(List<Long> productIds);

    /**
     * 查询商品介绍信息
     */
    MallProductDescEntity getByProductId(Long productId);

}

