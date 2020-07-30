package com.cheer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.dao.MallCategoryDao;
import com.cheer.dto.MallCategoryAddDTO;
import com.cheer.dto.MallCategoryProductSortDTO;
import com.cheer.dto.MallCategoryUpdateDTO;
import com.cheer.entity.MallCategoryEntity;
import com.cheer.entity.MallCategoryProductRelationEntity;
import com.cheer.exception.ServiceException;
import com.cheer.service.MallCategoryProductRelationService;
import com.cheer.service.MallCategoryService;
import com.cheer.service.MallProductService;
import com.cheer.vo.MallCategoryProductVO;
import com.cheer.vo.MallCategorySimpleVO;
import com.cheer.vo.MallProductSimpleVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("mallCategoryService")
public class MallCategoryServiceImpl extends ServiceImpl<MallCategoryDao, MallCategoryEntity> implements MallCategoryService {

    @Autowired
    private MallCategoryDao mallCategoryDao;

    @Autowired
    private MallCategoryProductRelationService mallCategoryProductRelationService;

    @Autowired
    private MallProductService mallProductService;

    @Override
    public List<MallCategoryProductVO> tree() {
        // 查询所有分类
        List<MallCategoryEntity> categoryEntityList = listAll();

        // 遍历分类，查询每个分类的商品
        return categoryEntityList.stream()
                .map(categoryEntity -> {

                    // 查询每个分类的商品id
                    List<MallCategoryProductRelationEntity> relationList = mallCategoryProductRelationService
                            .listByCategoryId(categoryEntity.getId());

                    // 查询商品信息
                    List<Long> productIdList = relationList.stream()
                            .map(MallCategoryProductRelationEntity::getProductId)
                            .collect(Collectors.toList());
                    Map<Long, MallProductSimpleVO> simpleProductMap =
                            mallProductService.listProductSimpleByIds(productIdList).stream()
                                    .collect(Collectors.toMap(MallProductSimpleVO::getId, Function.identity()));

                    // 按顺序添加商品信息
                    List<MallProductSimpleVO> simpleProductList = new ArrayList<>();
                    for (MallCategoryProductRelationEntity relation : relationList) {
                        MallProductSimpleVO simpleProduct = simpleProductMap.get(relation.getProductId());
                        if (simpleProduct != null) {
                            simpleProductList.add(simpleProduct);
                        }
                    }

                    return new MallCategoryProductVO()
                            .setId(categoryEntity.getId())
                            .setName(categoryEntity.getName())
                            .setProductList(simpleProductList);
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateSort(List<MallCategoryProductSortDTO> categoryProductSortDTOList) {
        // 修改分类排序
        for (int i = 0; i < categoryProductSortDTOList.size(); i++) {
            MallCategoryEntity category = new MallCategoryEntity()
                    .setId(categoryProductSortDTOList.get(i).getCategoryId())
                    .setSort((long) i);
            mallCategoryDao.updateById(category);
        }

        // 保存分类商品或修改分类下商品关系排序
        for (MallCategoryProductSortDTO sort : categoryProductSortDTOList) {
            List<Long> productIdList = sort.getProductIdList();
            if (CollectionUtils.isNotEmpty(productIdList)) {
                for (int i = 0; i < productIdList.size(); i++) {
                    mallCategoryProductRelationService.updateSort(sort.getCategoryId(), productIdList.get(i), (long) i);
                }
            }
        }
    }

    @Override
    public void add(MallCategoryAddDTO categoryAddDTO) {
        MallCategoryEntity category = new MallCategoryEntity()
                .setName(categoryAddDTO.getName());
        mallCategoryDao.insert(category);
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        // 删除分类
        mallCategoryDao.deleteBatchIds(ids);

        // 删除分类与商品关系
        mallCategoryProductRelationService.deleteByCategoryIds(ids);
    }

    @Override
    public void update(MallCategoryUpdateDTO categoryUpdateDTO) {
        MallCategoryEntity category = new MallCategoryEntity()
                .setId(categoryUpdateDTO.getId())
                .setName(categoryUpdateDTO.getName());
        mallCategoryDao.updateById(category);
    }

    @Override
    public List<MallCategoryEntity> listAll() {
        return mallCategoryDao.selectList();
    }

    @Override
    public List<MallCategorySimpleVO> listAllSimply() {
        List<MallCategoryEntity> categoryList = mallCategoryDao.selectName();

        return categoryList.stream()
                .map(categoryEntity -> new MallCategorySimpleVO()
                        .setId(categoryEntity.getId())
                        .setName(categoryEntity.getName()))
                .collect(Collectors.toList());
    }
}