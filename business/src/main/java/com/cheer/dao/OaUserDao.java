package com.cheer.dao;

import com.cheer.entity.OaUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息
 * 
 * @author cheer
 */
@Mapper
public interface OaUserDao extends BaseMapper<OaUserEntity> {
	
}
