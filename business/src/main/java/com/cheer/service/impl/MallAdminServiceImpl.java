package com.cheer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.dao.MallAdminDao;
import com.cheer.entity.MallAdminEntity;
import com.cheer.service.MallAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mallAdminService")
public class MallAdminServiceImpl extends ServiceImpl<MallAdminDao, MallAdminEntity> implements MallAdminService {

    @Autowired
    private MallAdminDao mallAdminDao;

    @Override
    public MallAdminEntity getByUsername(String username) {
        return mallAdminDao.selectOneByUsername(username);
    }
}