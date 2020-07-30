package com.cheer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.dao.MallProductDescDao;
import com.cheer.entity.MallProductDescEntity;
import com.cheer.service.MallProductDescService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mallProductDescService")
public class MallProductDescServiceImpl extends ServiceImpl<MallProductDescDao, MallProductDescEntity> implements MallProductDescService {

    @Autowired
    private MallProductDescDao mallProductDescDao;

    @Override
    public void saveOrUpdateByProductId(Long productId, String desc) {
        MallProductDescEntity productDesc = new MallProductDescEntity()
                .setProductId(productId)
                .setDescription(desc);

        saveOrUpdate(productDesc, new QueryWrapper<MallProductDescEntity>()
                .eq("product_id", productId));
    }

    @Override
    public void deleteByProductId(Long productId) {
        mallProductDescDao.deleteByProductId(productId);
    }

    @Override
    public void deleteByProductIds(List<Long> productIds) {
        if (CollectionUtils.isNotEmpty(productIds)) {
            mallProductDescDao.deleteByProductIds(productIds);
        }
    }

    @Override
    public MallProductDescEntity getByProductId(Long productId) {
        return mallProductDescDao.selectOneByProductId(productId);
    }
}