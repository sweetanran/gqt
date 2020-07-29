package com.cheer.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.common.vo.Page;
import com.cheer.common.vo.PageParam;
import com.cheer.common.utils.PageQuery;

import com.cheer.dao.OaUserDao;
import com.cheer.entity.OaUserEntity;
import com.cheer.service.OaUserService;

@Service("oaUserService")
public class OaUserServiceImpl extends ServiceImpl<OaUserDao, OaUserEntity> implements OaUserService {


}