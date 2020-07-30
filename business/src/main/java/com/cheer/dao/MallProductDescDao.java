package com.cheer.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cheer.entity.MallProductDescEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品介绍表
 * 
 * @author cheer
 */
@Mapper
@Repository
public interface MallProductDescDao extends BaseMapper<MallProductDescEntity> {

    default void deleteByProductId(Long productId) {
        delete(new QueryWrapper<MallProductDescEntity>()
                .eq("product_id", productId));
    }

    default void deleteByProductIds(List<Long> productIds) {
        delete(new QueryWrapper<MallProductDescEntity>()
                .in("product_id", productIds));
    }

    default MallProductDescEntity selectOneByProductId(Long productId) {
        return selectOne(new QueryWrapper<MallProductDescEntity>()
                .eq("product_id", productId)
                .last("limit 1"));
    }
}
