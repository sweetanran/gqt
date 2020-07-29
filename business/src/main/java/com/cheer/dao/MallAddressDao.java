package com.cheer.dao;

import com.cheer.entity.MallAddressEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收货地址表
 * 
 * @author cheer
 */
@Mapper
public interface MallAddressDao extends BaseMapper<MallAddressEntity> {
	
}
