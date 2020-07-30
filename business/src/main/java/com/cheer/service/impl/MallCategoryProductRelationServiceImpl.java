package com.cheer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.dao.MallCategoryProductRelationDao;
import com.cheer.dto.MallCategoryProductDTO;
import com.cheer.entity.MallCategoryProductRelationEntity;
import com.cheer.service.MallCategoryProductRelationService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("mallCategoryProductRelationService")
public class MallCategoryProductRelationServiceImpl extends ServiceImpl<MallCategoryProductRelationDao, MallCategoryProductRelationEntity> implements MallCategoryProductRelationService {

    @Autowired
    private MallCategoryProductRelationDao mallCategoryProductRelationDao;

    @Override
    public void add(MallCategoryProductDTO categoryProductDTO) {
        MallCategoryProductRelationEntity relation = mallCategoryProductRelationDao.selectOneByCategoryIdAndProductId(
                categoryProductDTO.getCategoryId(), categoryProductDTO.getProductId());

        if (relation == null) {
            MallCategoryProductRelationEntity insertRelation = new MallCategoryProductRelationEntity()
                    .setCategoryId(categoryProductDTO.getCategoryId())
                    .setProductId(categoryProductDTO.getProductId());
            mallCategoryProductRelationDao.insert(insertRelation);
        }
    }

    @Override
    public void delete(MallCategoryProductDTO categoryProductDTO) {
        mallCategoryProductRelationDao.deleteByCategoryIdAndProductId(categoryProductDTO.getCategoryId(),
                categoryProductDTO.getProductId());
    }

    @Override
    public void deleteByCategoryIds(List<Long> categoryIds) {
        mallCategoryProductRelationDao.deleteByCategoryIds(categoryIds);
    }

    @Override
    public void deleteByProductId(Long productId) {
        mallCategoryProductRelationDao.deleteByProductId(productId);
    }

    @Override
    public void deleteByProductIds(List<Long> productIds) {
        if(CollectionUtils.isNotEmpty(productIds)){
            mallCategoryProductRelationDao.deleteByProductIds(productIds);
        }
    }

    @Override
    public void updateSort(Long categoryId, Long productId, Long sort) {
        MallCategoryProductRelationEntity relation = new MallCategoryProductRelationEntity()
                .setSort(sort);
        mallCategoryProductRelationDao.updateByCategoryIdAndProductId(categoryId, productId, relation);
    }

    @Override
    public List<MallCategoryProductRelationEntity> listByCategoryId(Long categoryId) {
        return mallCategoryProductRelationDao.selectListByCategoryId(categoryId);
    }

    @Override
    public List<MallCategoryProductRelationEntity> listByProductIds(List<Long> productIds) {
        if (CollectionUtils.isEmpty(productIds)) {
            return new ArrayList<>();
        }

        return mallCategoryProductRelationDao.selectListByProductIds(productIds);
    }

    @Override
    public List<MallCategoryProductRelationEntity> listByProductId(Long productId) {
        return mallCategoryProductRelationDao.selectListByProductId(productId);
    }

}