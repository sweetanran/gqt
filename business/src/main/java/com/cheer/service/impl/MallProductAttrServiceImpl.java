package com.cheer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.dao.MallProductAttrDao;
import com.cheer.entity.MallProductAttrEntity;
import com.cheer.service.MallProductAttrService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mallProductAttrService")
public class MallProductAttrServiceImpl extends ServiceImpl<MallProductAttrDao, MallProductAttrEntity> implements MallProductAttrService {

    @Autowired
    private MallProductAttrDao mallProductAttrDao;

    @Override
    public void deleteByProductId(Long productId) {
        mallProductAttrDao.deleteByProductId(productId);
    }

    @Override
    public void deleteByProductIds(List<Long> productIds) {
        if(CollectionUtils.isNotEmpty(productIds)){
            mallProductAttrDao.deleteByProductIds(productIds);
        }
    }

    @Override
    public List<MallProductAttrEntity> listByProductId(Long productId) {
        return mallProductAttrDao.selectListByProductId(productId);
    }
}