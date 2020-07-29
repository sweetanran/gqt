package com.cheer.dao;

import com.cheer.entity.MallProductEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品表
 * 
 * @author cheer
 */
@Mapper
public interface MallProductDao extends BaseMapper<MallProductEntity> {
	
}
