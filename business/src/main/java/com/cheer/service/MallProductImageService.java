package com.cheer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cheer.entity.MallProductImageEntity;

import java.util.List;

/**
 * 商品图片表
 *
 * @author cheer
 */
public interface MallProductImageService extends IService<MallProductImageEntity> {

    /**
     * 删除商品图片
     */
    void deleteByProductId(Long productId);

    /**
     * 删除商品图片
     */
    void deleteByProductIds(List<Long> productIds);

    /**
     * 查询商品图片
     */
    List<MallProductImageEntity> listByProductId(Long productId);

}

