package com.cheer.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cheer.entity.MallProductImageEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品图片表
 *
 * @author cheer
 */
@Mapper
@Repository
public interface MallProductImageDao extends BaseMapper<MallProductImageEntity> {

    default void deleteByProductId(Long productId) {
        delete(new QueryWrapper<MallProductImageEntity>()
                .eq("product_id", productId));
    }

    default void deleteByProductIds(List<Long> productIds) {
        delete(new QueryWrapper<MallProductImageEntity>()
                .in("product_id", productIds));
    }

    default List<MallProductImageEntity> selectListByProductId(Long productId) {
        return selectList(new QueryWrapper<MallProductImageEntity>()
                .eq("product_id", productId)
                .orderByAsc("sort"));
    }

}
