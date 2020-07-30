package com.cheer.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheer.dto.MallProductQueryDTO;
import com.cheer.entity.MallProductEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 商品表
 *
 * @author cheer
 */
@Mapper
@Repository
public interface MallProductDao extends BaseMapper<MallProductEntity> {

    default void update(MallProductEntity product) {
        UpdateWrapper<MallProductEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("type", product.getType());
        updateWrapper.set("main_title", product.getMainTitle());
        updateWrapper.set("short_title", product.getShortTitle());
        updateWrapper.set("stock", product.getStock());
        updateWrapper.set("publish_status", product.getPublishStatus());

        if (product.getMarketPrice() == null) {
            updateWrapper.set("market_price", null);
        } else {
            updateWrapper.set("market_price", product.getMarketPrice());
        }

        if (product.getDisplayPrice() == null) {
            updateWrapper.set("display_price", null);
        } else {
            updateWrapper.set("display_price", product.getDisplayPrice());
        }

        if (product.getCredit() == null) {
            updateWrapper.set("credit", null);
        } else {
            updateWrapper.set("credit", product.getCredit());
        }

        if (StringUtils.isBlank(product.getKeywords())) {
            updateWrapper.set("keywords", null);
        } else {
            updateWrapper.set("keywords", product.getKeywords());
        }

        update(null, updateWrapper.lambda().eq(MallProductEntity::getId, product.getId()));
    }

    default List<MallProductEntity> selectMainTitleByIds(List<Long> ids) {
        return selectList(new QueryWrapper<MallProductEntity>()
                .select("id", "main_title")
                .in("id", ids));
    }

    default IPage<MallProductEntity> selectPageByQueryCondition(MallProductQueryDTO productQueryDTO) {
        QueryWrapper<MallProductEntity> queryWrapper = new QueryWrapper<>();

        String name = StringUtils.trimToNull(productQueryDTO.getName());
        if (name != null) {
            queryWrapper.and(mallProductEntityQueryWrapper -> mallProductEntityQueryWrapper
                    .like("main_title", name)
                    .or()
                    .like("short_title", name));
        }

        Date date = productQueryDTO.getCreateDate();
        if (date != null) {
            queryWrapper.and(mallProductEntityQueryWrapper -> mallProductEntityQueryWrapper
                    .ge("create_time", date)
                    .lt("create_time", DateUtils.addDays(date, 1)));
        }

        Integer publishStatus = productQueryDTO.getPublishStatus();
        if (publishStatus != null) {
            queryWrapper.eq("publish_status", publishStatus);
        }

        queryWrapper.orderByDesc("create_time");

        return selectPage(new Page<>(productQueryDTO.getPageNum(), productQueryDTO.getPageSize()), queryWrapper);
    }

    default Integer selectCountByShopId(Long shopId) {
        return selectCount(new QueryWrapper<MallProductEntity>().eq("shop_id", shopId));
    }

}
