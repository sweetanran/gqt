package com.cheer.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.common.Page;
import com.cheer.dao.MallProductDao;
import com.cheer.dto.MallProductAddDTO;
import com.cheer.dto.MallProductQueryDTO;
import com.cheer.dto.MallProductUpdateDTO;
import com.cheer.entity.*;
import com.cheer.enums.ProductImageEnum;
import com.cheer.enums.ProductStatusEnum;
import com.cheer.exception.ServiceException;
import com.cheer.service.*;
import com.cheer.vo.ImageVO;
import com.cheer.vo.MallProductInfoVO;
import com.cheer.vo.MallProductListVO;
import com.cheer.vo.MallProductSimpleVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("mallProductService")
public class MallProductServiceImpl extends ServiceImpl<MallProductDao, MallProductEntity> implements MallProductService {

    @Value("${image.url}")
    private String imageUrl;

    @Autowired
    private MallProductDao mallProductDao;

    @Autowired
    private MallCategoryProductRelationService mallCategoryProductRelationService;

    @Autowired
    private MallCategoryService mallCategoryService;

    @Autowired
    private MallShopService mallShopService;

    @Autowired
    private MallProductImageService mallProductImageService;

    @Autowired
    private MallProductAttrService mallProductAttrService;

    @Autowired
    private MallProductDescService mallProductDescService;

    @Override
    public Page<MallProductListVO> listByQueryCondition(MallProductQueryDTO productQueryDTO) {
        // 分页查询商品信息
        IPage<MallProductEntity> page = mallProductDao.selectPageByQueryCondition(productQueryDTO);

        // 查询每个商品包含的分类
        List<Long> productIdList = page.getRecords().stream()
                .map(MallProductEntity::getId)
                .collect(Collectors.toList());
        Map<Long, List<Long>> productCategoryMap = mallCategoryProductRelationService.listByProductIds(productIdList).stream()
                .collect(Collectors.groupingBy(MallCategoryProductRelationEntity::getProductId,
                        Collectors.mapping(MallCategoryProductRelationEntity::getCategoryId, Collectors.toList())));

        // 查询分类信息
        Set<Long> categoryIdSet = new HashSet<>();
        productCategoryMap.values().forEach(categoryIdSet::addAll);
        Map<Long, MallCategoryEntity> categoryMap;
        if (categoryIdSet.isEmpty()) {
            categoryMap = new HashMap<>();
        } else {
            categoryMap = mallCategoryService.listByIds(new ArrayList<>(categoryIdSet)).stream()
                    .collect(Collectors.toMap(MallCategoryEntity::getId, Function.identity()));
        }

        // 转换商品vo
        List<MallProductListVO> productList = page.getRecords().stream()
                .map(productEntity -> {

                    // 基础信息
                    MallProductListVO productListVO = new MallProductListVO()
                            .setId(productEntity.getId())
                            .setNumber(productEntity.getNumber())
                            .setName(productEntity.getMainTitle())
                            .setCreateTime(DateUtil.formatDateTime(productEntity.getCreateTime()))
                            .setPublishStatus(productEntity.getPublishStatus())
                            .setStock(productEntity.getStock());

                    // 价格积分
                    if (productEntity.getMarketPrice() != null) {
                        productListVO.setMarketPrice(productEntity.getMarketPrice().stripTrailingZeros().toPlainString() + "元");
                    }
                    String displayPrice = null;
                    if (productEntity.getDisplayPrice() != null) {
                        displayPrice = productEntity.getMarketPrice().stripTrailingZeros().toPlainString() + "元";
                    }
                    if (displayPrice != null) {
                        displayPrice += "+";
                    }
                    if (productEntity.getCredit() != null) {
                        displayPrice = productEntity.getCredit() + "元";
                    }
                    if (displayPrice != null) {
                        productListVO.setDisplayPrice(displayPrice);
                    }

                    // 分类信息
                    List<Long> categoryIdList = productCategoryMap.get(productEntity.getId());
                    if (CollectionUtils.isNotEmpty(categoryIdList)) {
                        List<String> categoryNameList = new ArrayList<>();
                        for (Long categoryId : categoryIdList) {
                            MallCategoryEntity category = categoryMap.get(categoryId);
                            if (category != null) {
                                categoryNameList.add(category.getName());
                            }
                        }

                        if (!categoryNameList.isEmpty()) {
                            productListVO.setCategory(StringUtils.join(categoryNameList, "，"));
                        }
                    }

                    return productListVO;
                })
                .collect(Collectors.toList());

        return new Page<>(page, productList);
    }

    @Override
    @Transactional
    public void add(MallProductAddDTO productAddDTO) {
        // 保存商品表
        MallProductEntity productEntity = new MallProductEntity()
                .setNumber(generateNumber(productAddDTO.getShopId()))
                .setType(productAddDTO.getProductType())
                .setMainTitle(productAddDTO.getMainTitle())
                .setShortTitle(productAddDTO.getShortTitle())
                .setShopId(productAddDTO.getShopId())
                .setStock(productAddDTO.getStock())
                .setPublishStatus(productAddDTO.getSubmitType() == 1 ?
                        ProductStatusEnum.PEND_REVIEW.getCode() : ProductStatusEnum.NOT_ACTIVATED.getCode());

        if (productAddDTO.getMarketPrice() != null) {
            productEntity.setMarketPrice(new BigDecimal(productAddDTO.getMarketPrice()));
        }
        if (productAddDTO.getDisplayPrice() != null) {
            productEntity.setDisplayPrice(new BigDecimal(productAddDTO.getDisplayPrice()));
        }
        if (productAddDTO.getCredit() != null) {
            if (!NumberUtil.isInteger(productAddDTO.getCredit()) || Integer.valueOf(productAddDTO.getCredit()) < 0) {
                throw new ServiceException("积分只能输入非负整数");
            }
            productEntity.setCredit(Integer.valueOf(productAddDTO.getCredit()));
        }

        if (StringUtils.isNotBlank(productAddDTO.getKeywords())) {
            productAddDTO.setKeywords(StringUtils.replace(productAddDTO.getKeywords(), "，", ","));
        }

        mallProductDao.insert(productEntity);

        // 保存商品图片表
        saveProductImage(productEntity.getId(), productAddDTO.getThumbnailUrl(),
                productAddDTO.getDisplayImageUrl(), productAddDTO.getDescImageUrl());


        // 保存分类
        saveCategoryProduct(productEntity.getId(), productAddDTO.getCategoryIdList());

        // 保存规格
        saveProductAttr(productEntity.getId(), productAddDTO.getAttr());


        // 保存简介
        if (StringUtils.isNotBlank(productAddDTO.getDesc())) {
            MallProductDescEntity desc = new MallProductDescEntity();
            desc.setProductId(productEntity.getId());
            desc.setDescription(productAddDTO.getDesc());
            mallProductDescService.save(desc);
        }
    }

    @Override
    public MallProductInfoVO info(Long id) {
        MallProductEntity product = mallProductDao.selectById(id);
        if (product == null) {
            throw new ServiceException("商品不存在");
        }

        // 商品基本信息
        MallProductInfoVO info = new MallProductInfoVO()
                .setId(product.getId())
                .setType(product.getType())
                .setMainTitle(product.getMainTitle())
                .setShortTitle(product.getShortTitle())
                .setShopId(product.getShopId())
                .setStock(product.getStock())
                .setKeywords(product.getKeywords());

        if (product.getMarketPrice() != null) {
            info.setMarketPrice(product.getMarketPrice().stripTrailingZeros().toPlainString());
        }
        if (product.getDisplayPrice() != null) {
            info.setDisplayPrice(product.getDisplayPrice().stripTrailingZeros().toPlainString());
        }
        if (product.getCredit() != null) {
            info.setCredit(String.valueOf(product.getCredit()));
        }

        // 商品图片
        List<MallProductImageEntity> imageList = mallProductImageService.listByProductId(id);
        List<ImageVO> displayImageUrl = new ArrayList<>();
        List<ImageVO> descImageUrl = new ArrayList<>();
        for (MallProductImageEntity image : imageList) {
            if (image.getImageType().equals(ProductImageEnum.THUMBNAIL.getCode())) {

                info.setThumbnailUrl(new ImageVO()
                        .setFullUrl(imageUrl + image.getImageUrl())
                        .setShortUrl(image.getImageUrl()));

            } else if (image.getImageType().equals(ProductImageEnum.DISPLAY.getCode())) {

                ImageVO imageVO = new ImageVO();
                imageVO.setFullUrl(imageUrl + image.getImageUrl());
                imageVO.setShortUrl(image.getImageUrl());
                displayImageUrl.add(imageVO);

            } else if (image.getImageType().equals(ProductImageEnum.DESC.getCode())) {

                ImageVO imageVO = new ImageVO();
                imageVO.setFullUrl(imageUrl + image.getImageUrl());
                imageVO.setShortUrl(image.getImageUrl());
                descImageUrl.add(imageVO);
            }
        }
        info.setDisplayImageUrl(displayImageUrl);
        info.setDescImageUrl(descImageUrl);

        // 商品分类
        List<MallCategoryProductRelationEntity> relation = mallCategoryProductRelationService.listByProductId(id);
        info.setCategoryIdList(relation.stream()
                .map(MallCategoryProductRelationEntity::getCategoryId)
                .collect(Collectors.toList()));

        // 商品规格
        List<MallProductAttrEntity> attrList = mallProductAttrService.listByProductId(id);
        info.setAttr(attrList.stream()
                .map(MallProductAttrEntity::getAttrValue)
                .collect(Collectors.toList()));

        // 商品简介
        MallProductDescEntity desc = mallProductDescService.getByProductId(id);
        if (desc != null) {
            info.setDesc(desc.getDescription());
        }

        return info;
    }

    @Override
    @Transactional
    public void update(MallProductUpdateDTO productUpdateDTO) {
        MallProductEntity product = mallProductDao.selectById(productUpdateDTO.getId());
        if (product == null) {
            throw new ServiceException("商品不存在");
        }
        if (product.getPublishStatus().equals(ProductStatusEnum.REMOVED.getCode())) {
            throw new ServiceException("已下架的商品不能修改");
        }
        if (!Objects.equals(product.getShopId(), productUpdateDTO.getShopId())) {
            throw new ServiceException("商户Id不能修改");
        }

        // 更新商品表
        MallProductEntity updateProduct = new MallProductEntity();
        updateProduct.setId(productUpdateDTO.getId())
                .setType(productUpdateDTO.getProductType())
                .setMainTitle(productUpdateDTO.getMainTitle())
                .setShortTitle(productUpdateDTO.getShortTitle())
                .setStock(productUpdateDTO.getStock())
                .setPublishStatus(productUpdateDTO.getSubmitType() == 1 ?
                        ProductStatusEnum.PEND_REVIEW.getCode() : ProductStatusEnum.NOT_ACTIVATED.getCode());

        if (StringUtils.isNotBlank(productUpdateDTO.getKeywords())) {
            updateProduct.setKeywords(StringUtils.replace(productUpdateDTO.getKeywords(), "，", ","));
        }

        if (productUpdateDTO.getMarketPrice() != null) {
            updateProduct.setMarketPrice(new BigDecimal(productUpdateDTO.getMarketPrice()));
        }
        if (productUpdateDTO.getDisplayPrice() != null) {
            updateProduct.setDisplayPrice(new BigDecimal(productUpdateDTO.getDisplayPrice()));
        }
        if (productUpdateDTO.getCredit() != null) {
            if (!NumberUtil.isInteger(productUpdateDTO.getCredit()) || Integer.valueOf(productUpdateDTO.getCredit()) < 0) {
                throw new ServiceException("积分只能输入非负整数");
            }
            updateProduct.setCredit(Integer.valueOf(productUpdateDTO.getCredit()));
        }

        mallProductDao.update(updateProduct);

        // 更新商品图片
        mallProductImageService.deleteByProductId(productUpdateDTO.getId());

        saveProductImage(productUpdateDTO.getId(), productUpdateDTO.getThumbnailUrl(),
                productUpdateDTO.getDisplayImageUrl(), productUpdateDTO.getDescImageUrl());

        // 更新商品分类
        mallCategoryProductRelationService.deleteByProductId(productUpdateDTO.getId());

        saveCategoryProduct(productUpdateDTO.getId(), productUpdateDTO.getCategoryIdList());

        // 更新商品规格
        mallProductAttrService.deleteByProductId(productUpdateDTO.getId());

        saveProductAttr(productUpdateDTO.getId(), productUpdateDTO.getAttr());

        // 保存简介
        if (StringUtils.isNotBlank(productUpdateDTO.getDesc())) {
            mallProductDescService.saveOrUpdateByProductId(productUpdateDTO.getId(), productUpdateDTO.getDesc());
        } else {
            mallProductDescService.deleteByProductId(productUpdateDTO.getId());
        }
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        // 删除商品表
        mallProductDao.deleteBatchIds(ids);

        // 删除图片表
        mallProductImageService.deleteByProductIds(ids);

        // 删除商品分类关联表
        mallCategoryProductRelationService.deleteByProductIds(ids);

        // 删除规格表
        mallProductAttrService.deleteByProductIds(ids);

        // 删除简介表
        mallProductDescService.deleteByProductIds(ids);
    }

    @Override
    public void review(Long id) {
        MallProductEntity product = mallProductDao.selectById(id);
        if (product == null) {
            throw new ServiceException("商品不存在");
        }
        if (!Objects.equals(product.getPublishStatus(), ProductStatusEnum.PEND_REVIEW.getCode())) {
            throw new ServiceException("只有待审核的商品可以进行审核");
        }

        MallProductEntity updateProduct = new MallProductEntity()
                .setId(id)
                .setPublishStatus(ProductStatusEnum.PASSED.getCode());
        mallProductDao.updateById(updateProduct);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        MallProductEntity product = mallProductDao.selectById(id);
        if (product == null) {
            throw new ServiceException("商品不存在");
        }
        if (!Objects.equals(product.getPublishStatus(), ProductStatusEnum.PASSED.getCode())) {
            throw new ServiceException("只有已上架的商品可以进行下架操作");
        }

        MallProductEntity updateProduct = new MallProductEntity();
        updateProduct.setId(id);
        updateProduct.setPublishStatus(ProductStatusEnum.REMOVED.getCode());
        mallProductDao.updateById(updateProduct);

        mallCategoryProductRelationService.deleteByProductId(id);
    }

    @Override
    public List<MallProductSimpleVO> listProductSimpleByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        List<MallProductEntity> productList = mallProductDao.selectMainTitleByIds(ids);

        return productList.stream()
                .map(mallProductEntity -> new MallProductSimpleVO()
                        .setId(mallProductEntity.getId())
                        .setName(mallProductEntity.getMainTitle()))
                .collect(Collectors.toList());
    }

    /**
     * 生成编号
     */
    private String generateNumber(Long shopId) {
        MallShopEntity shop = mallShopService.getById(shopId);
        if (shop == null) {
            throw new ServiceException("商户不存在");
        }

        Integer count = mallProductDao.selectCountByShopId(shopId);
        String currCount = StringUtils.leftPad(String.valueOf(count + 1), 3, '0');

        return shop.getNumber() + currCount;
    }

    /**
     * 保存商品图片
     */
    private void saveProductImage(Long productId, String thumbnailUrl, List<String> displayImageUrl, List<String> descImageUrl) {
        List<MallProductImageEntity> imageList = new ArrayList<>();

        MallProductImageEntity thumbnail = new MallProductImageEntity()
                .setProductId(productId)
                .setImageType(ProductImageEnum.THUMBNAIL.getCode())
                .setImageUrl(thumbnailUrl);
        imageList.add(thumbnail);

        for (int i = 0; i < displayImageUrl.size(); i++) {
            MallProductImageEntity displayImage = new MallProductImageEntity()
                    .setProductId(productId)
                    .setImageType(ProductImageEnum.DISPLAY.getCode())
                    .setImageUrl(displayImageUrl.get(i))
                    .setSort((long) i);
            imageList.add(displayImage);
        }

        if (CollectionUtils.isNotEmpty(descImageUrl)) {
            for (int i = 0; i < descImageUrl.size(); i++) {
                MallProductImageEntity descImage = new MallProductImageEntity()
                        .setProductId(productId)
                        .setImageType(ProductImageEnum.DESC.getCode())
                        .setImageUrl(descImageUrl.get(i))
                        .setSort((long) i);
                imageList.add(descImage);
            }
        }

        mallProductImageService.saveBatch(imageList);
    }

    /**
     * 保存商品分类关联
     */
    private void saveCategoryProduct(Long productId, List<Long> categoryIdList) {
        if (CollectionUtils.isNotEmpty(categoryIdList)) {
            List<MallCategoryProductRelationEntity> relationList = new ArrayList<>();
            for (Long categoryId : categoryIdList) {
                MallCategoryProductRelationEntity relation = new MallCategoryProductRelationEntity()
                        .setProductId(productId)
                        .setCategoryId(categoryId);
                relationList.add(relation);
            }
            mallCategoryProductRelationService.saveBatch(relationList);
        }
    }

    /**
     * 保存商品规格
     */
    private void saveProductAttr(Long productId, List<String> attrs) {
        if (CollectionUtils.isNotEmpty(attrs)) {
            List<MallProductAttrEntity> attrList = new ArrayList<>();
            for (String attr : attrs) {
                MallProductAttrEntity productAttr = new MallProductAttrEntity()
                        .setProductId(productId)
                        .setAttrValue(attr);
                attrList.add(productAttr);
            }
            mallProductAttrService.saveBatch(attrList);
        }
    }
}