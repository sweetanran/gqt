package com.cheer.dao;

import com.cheer.entity.MallAdminEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理员表
 * 
 * @author cheer
 */
@Mapper
public interface MallAdminDao extends BaseMapper<MallAdminEntity> {
	
}
