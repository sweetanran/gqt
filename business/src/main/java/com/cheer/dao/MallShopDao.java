package com.cheer.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cheer.entity.MallShopEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 商户表
 * 
 * @author cheer
 */
@Mapper
@Repository
public interface MallShopDao extends BaseMapper<MallShopEntity> {

    default MallShopEntity selectOneByPhone(String phone) {
        return selectOne(new QueryWrapper<MallShopEntity>()
                .eq("phone", phone)
                .last("limit 1"));
    }
}
