package com.cheer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.dao.MallProductImageDao;
import com.cheer.entity.MallProductImageEntity;
import com.cheer.service.MallProductImageService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mallProductImageService")
public class MallProductImageServiceImpl extends ServiceImpl<MallProductImageDao, MallProductImageEntity> implements MallProductImageService {

    @Autowired
    private MallProductImageDao mallProductImageDao;

    @Override
    public void deleteByProductId(Long productId) {
        mallProductImageDao.deleteByProductId(productId);
    }

    @Override
    public void deleteByProductIds(List<Long> productIds) {
        if (CollectionUtils.isNotEmpty(productIds)) {
            mallProductImageDao.deleteByProductIds(productIds);
        }
    }

    @Override
    public List<MallProductImageEntity> listByProductId(Long productId) {
        return mallProductImageDao.selectListByProductId(productId);
    }
}