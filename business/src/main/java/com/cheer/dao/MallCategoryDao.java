package com.cheer.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheer.entity.MallCategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分类表
 *
 * @author cheer
 */
@Mapper
@Repository
public interface MallCategoryDao extends BaseMapper<MallCategoryEntity> {

    default List<MallCategoryEntity> selectList() {
        return selectList(new QueryWrapper<MallCategoryEntity>()
                .orderByAsc("sort"));
    }

    default List<MallCategoryEntity> selectName() {
        return selectList(new QueryWrapper<MallCategoryEntity>()
                .select("id", "name")
                .orderByAsc("sort"));
    }
}
