package com.cheer.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cheer.entity.MallProductAttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品属性表
 * 
 * @author cheer
 */
@Mapper
@Repository
public interface MallProductAttrDao extends BaseMapper<MallProductAttrEntity> {

    default void deleteByProductId(Long productId){
        delete(new QueryWrapper<MallProductAttrEntity>()
                .eq("product_id", productId));
    }

    default void deleteByProductIds(List<Long> productIds){
        delete(new QueryWrapper<MallProductAttrEntity>()
                .in("product_id", productIds));
    }

    default List<MallProductAttrEntity> selectListByProductId(Long productId) {
        return selectList(new QueryWrapper<MallProductAttrEntity>()
                .eq("product_id", productId)
                .orderByAsc("id"));
    }

}
