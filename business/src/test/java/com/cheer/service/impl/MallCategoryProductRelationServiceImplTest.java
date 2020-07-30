package com.cheer.service.impl;

import cn.hutool.core.date.DateUtil;
import com.cheer.dao.MallProductDao;
import com.cheer.dto.MallProductQueryDTO;
import com.cheer.entity.MallProductDescEntity;
import com.cheer.entity.MallProductEntity;
import com.cheer.service.MallCategoryProductRelationService;
import com.cheer.service.MallProductDescService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


/**
 * @author cheer
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MallCategoryProductRelationServiceImplTest {

    @Autowired
    private MallCategoryProductRelationService mallCategoryProductRelationService;

    @Autowired
    private MallProductDao mallProductDao;

    @Autowired
    private MallProductDescService mallProductDescService;

    @Test
    public void updateSort() {
        mallCategoryProductRelationService.updateSort(1000L, 1000L, 1000L);
    }

    @Test
    public void selectPageByQueryCondition() {
        MallProductQueryDTO mallProductQueryDTO = new MallProductQueryDTO();
        mallProductQueryDTO.setName("测试");
        mallProductQueryDTO.setCreateDate(DateUtil.beginOfDay(new Date()));
        mallProductQueryDTO.setPublishStatus(1);
        mallProductDao.selectPageByQueryCondition(mallProductQueryDTO);
    }

    @Test
    public void updateProduct() {
        MallProductEntity product = new MallProductEntity()
                .setId(10000L)
                .setKeywords("asd")
                .setNumber("123");
        mallProductDao.update(product);
    }

    @Test
    public void saveOrUpdate() {
        mallProductDescService.saveOrUpdateByProductId(1000L, "测试");
    }
}