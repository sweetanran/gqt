package com.cheer.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheer.entity.MallProductEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品表
 * 
 * @author cheer
 */
@Mapper
@Repository
public interface MallProductDao extends BaseMapper<MallProductEntity> {

    default List<MallProductEntity> selectMainTitleByIds(List<Long> ids) {
        return selectList(new QueryWrapper<MallProductEntity>()
                .select("id", "main_title")
                .in("id", ids));
    }

}
