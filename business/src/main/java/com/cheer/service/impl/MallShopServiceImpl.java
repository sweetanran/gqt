package com.cheer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.dao.MallShopDao;
import com.cheer.entity.MallShopEntity;
import com.cheer.service.MallShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mallShopService")
public class MallShopServiceImpl extends ServiceImpl<MallShopDao, MallShopEntity> implements MallShopService {

    @Autowired
    private MallShopDao mallShopDao;

    @Override
    public MallShopEntity getByPhone(String phone) {
        return mallShopDao.selectOneByPhone(phone);
    }
}