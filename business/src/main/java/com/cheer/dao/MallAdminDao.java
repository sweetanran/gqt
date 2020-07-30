package com.cheer.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cheer.entity.MallAdminEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 管理员表
 * 
 * @author cheer
 */
@Mapper
@Repository
public interface MallAdminDao extends BaseMapper<MallAdminEntity> {

    default MallAdminEntity selectOneByUsername(String username) {
        return selectOne(new QueryWrapper<MallAdminEntity>()
                .eq("username", username)
                .last("limit 1"));
    }
}
