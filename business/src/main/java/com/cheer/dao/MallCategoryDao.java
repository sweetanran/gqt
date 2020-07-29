package com.cheer.dao;

import com.cheer.entity.MallCategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类表
 * 
 * @author cheer
 */
@Mapper
public interface MallCategoryDao extends BaseMapper<MallCategoryEntity> {
	
}
