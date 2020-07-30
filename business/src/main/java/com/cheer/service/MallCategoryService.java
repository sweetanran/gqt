package com.cheer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cheer.dto.MallCategoryAddDTO;
import com.cheer.dto.MallCategoryProductSortDTO;
import com.cheer.dto.MallCategoryUpdateDTO;
import com.cheer.entity.MallCategoryEntity;
import com.cheer.vo.MallCategoryProductVO;
import com.cheer.vo.MallCategorySimpleVO;

import java.util.List;

/**
 * 分类表
 *
 * @author cheer
 */
public interface MallCategoryService extends IService<MallCategoryEntity> {

    /**
     * 查询分类与商品的树形结构
     */
    List<MallCategoryProductVO> tree();

    /**
     * 修改分类的排序以及分类下商品的排序
     */
    void updateSort(List<MallCategoryProductSortDTO> categoryProductSortDTOList);

    /**
     * 创建新的分类
     */
    void add(MallCategoryAddDTO categoryAddDTO);

    /**
     * 删除所有分类
     */
    void delete(List<Long> ids);

    /**
     * 修改分类信息
     */
    void update(MallCategoryUpdateDTO categoryUpdateDTO);

    /**
     * 查询所有分类
     */
    List<MallCategoryEntity> listAll();

    /**
     * 查询所有分类简要信息
     */
    List<MallCategorySimpleVO> listAllSimply();

}


