package com.cheer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.dao.OaUserDao;
import com.cheer.entity.OaUserEntity;
import com.cheer.service.OaUserService;
import org.springframework.stereotype.Service;

@Service("oaUserService")
public class OaUserServiceImpl extends ServiceImpl<OaUserDao, OaUserEntity> implements OaUserService {


}