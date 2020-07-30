package com.cheer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheer.dao.MallProductDao;
import com.cheer.entity.MallProductEntity;
import com.cheer.service.MallProductService;
import com.cheer.vo.MallProductSimpleVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("mallProductService")
public class MallProductServiceImpl extends ServiceImpl<MallProductDao, MallProductEntity> implements MallProductService {

    @Autowired
    private MallProductDao mallProductDao;

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
}