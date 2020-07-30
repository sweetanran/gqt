package com.cheer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cheer.dto.MallCategoryProductDTO;
import com.cheer.entity.MallCategoryProductRelationEntity;

import java.util.List;

/**
 * 分类与商品关系表
 *
 * @author cheer
 */
public interface MallCategoryProductRelationService extends IService<MallCategoryProductRelationEntity> {

    /**
     * 添加分类下的商品
     */
    void add(MallCategoryProductDTO categoryProductDTO);

    /**
     * 删除分类下的商品
     */
    void delete(MallCategoryProductDTO categoryProductDTO);

    /**
     * 删除分类id下的分类与商品关联
     */
    void deleteByCategoryIds(List<Long> categoryIds);

    /**
     * 刪除商品的分类关联
     */
    void deleteByProductId(Long productId);

    /**
     * 刪除商品的分类关联
     */
    void deleteByProductIds(List<Long> productIds);

    /**
     * 修改分类下商品的排序
     */
    void updateSort(Long categoryId, Long productId, Long sort);

    /**
     * 查询分类id下的分类与商品关联
     */
    List<MallCategoryProductRelationEntity> listByCategoryId(Long categoryId);

    /**
     * 查询商品集合关联的分类
     */
    List<MallCategoryProductRelationEntity> listByProductIds(List<Long> productIds);

    /**
     * 查询商品关联的分类
     */
    List<MallCategoryProductRelationEntity> listByProductId(Long productId);

}

