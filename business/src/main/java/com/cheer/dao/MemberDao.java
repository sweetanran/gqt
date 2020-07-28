package com.cheer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheer.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * 
 * @author cheer
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
