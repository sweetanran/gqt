package com.cheer.service.impl;

import com.cheer.service.MallCategoryProductRelationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author cheer
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MallCategoryProductRelationServiceImplTest {

    @Autowired
    private MallCategoryProductRelationService mallCategoryProductRelationService;

    @Test
    public void updateSort() {
        mallCategoryProductRelationService.updateSort(1000L, 1000L, 1000L);
    }
}