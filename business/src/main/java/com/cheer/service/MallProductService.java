package com.cheer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cheer.common.Page;
import com.cheer.dto.MallProductAddDTO;
import com.cheer.dto.MallProductQueryDTO;
import com.cheer.dto.MallProductUpdateDTO;
import com.cheer.entity.MallProductEntity;
import com.cheer.vo.MallProductInfoVO;
import com.cheer.vo.MallProductListVO;
import com.cheer.vo.MallProductSimpleVO;

import java.util.List;

/**
 * 商品表
 *
 * @author cheer
 */
public interface MallProductService extends IService<MallProductEntity> {

    /**
     * 查询商品分页列表
     */
    Page<MallProductListVO> listByQueryCondition(MallProductQueryDTO productQueryDTO);

    /**
     * 创建商品
     */
    void add(MallProductAddDTO productAddDTO);

    /**
     * 查询商品详情
     */
    MallProductInfoVO info(Long id);

    /**
     * 修改商品
     */
    void update(MallProductUpdateDTO productUpdateDTO);

    /**
     * 删除商品
     */
    void delete(List<Long> ids);

    /**
     * 删除商品
     */
    void review(Long id);

    /**
     * 下架商品
     */
    void remove(Long id);

    /**
     * 查询商品简要信息
     */
    List<MallProductSimpleVO> listProductSimpleByIds(List<Long> ids);

}

