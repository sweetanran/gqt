package com.cheer.dao;

import com.cheer.entity.MallCategoryProductRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类与商品关系表
 * 
 * @author cheer
 */
@Mapper
public interface MallCategoryProductRelationDao extends BaseMapper<MallCategoryProductRelationEntity> {
	
}
