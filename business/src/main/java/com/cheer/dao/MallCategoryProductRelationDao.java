package com.cheer.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheer.entity.MallCategoryProductRelationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分类与商品关系表
 *
 * @author cheer
 */
@Mapper
@Repository
public interface MallCategoryProductRelationDao extends BaseMapper<MallCategoryProductRelationEntity> {

    default void deleteByCategoryIds(List<Long> categoryIds) {
        delete(new QueryWrapper<MallCategoryProductRelationEntity>()
                .in("category_id", categoryIds));
    }

    default void deleteByCategoryIdAndProductId(Long categoryId, Long productId) {
        delete(new QueryWrapper<MallCategoryProductRelationEntity>()
                .eq("category_id", categoryId)
                .eq("product_id", productId));
    }

    default void deleteByProductId(Long productId) {
        delete(new QueryWrapper<MallCategoryProductRelationEntity>()
                .eq("product_id", productId));
    }

    default void deleteByProductIds(List<Long> productIds) {
        delete(new QueryWrapper<MallCategoryProductRelationEntity>()
                .in("product_id", productIds));
    }

    default void updateByCategoryIdAndProductId(Long categoryId, Long productId, MallCategoryProductRelationEntity relation) {
        update(relation, new QueryWrapper<MallCategoryProductRelationEntity>()
                .eq("category_id", categoryId)
                .eq("product_id", productId));
    }

    default List<MallCategoryProductRelationEntity> selectListByCategoryId(Long categoryId) {
        return selectList(new QueryWrapper<MallCategoryProductRelationEntity>()
                .eq("category_id", categoryId)
                .orderByAsc("sort"));
    }

    default MallCategoryProductRelationEntity selectOneByCategoryIdAndProductId(Long categoryId, Long productId) {
        return selectOne(new QueryWrapper<MallCategoryProductRelationEntity>()
                .eq("category_id", categoryId)
                .eq("product_id", productId)
                .last("limit 1"));
    }

    default List<MallCategoryProductRelationEntity> selectListByProductIds(List<Long> productIds) {
        return selectList(new QueryWrapper<MallCategoryProductRelationEntity>()
                .in("product_id", productIds));
    }

    default List<MallCategoryProductRelationEntity> selectListByProductId(Long productId) {
        return selectList(new QueryWrapper<MallCategoryProductRelationEntity>()
                .eq("product_id", productId));
    }

}
