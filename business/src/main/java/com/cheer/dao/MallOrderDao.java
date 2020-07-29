package com.cheer.dao;

import com.cheer.entity.MallOrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表
 * 
 * @author cheer
 */
@Mapper
public interface MallOrderDao extends BaseMapper<MallOrderEntity> {
	
}
