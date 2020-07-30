package com.cheer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cheer.entity.MallProductEntity;
import com.cheer.vo.MallProductSimpleVO;

import java.util.List;

/**
 * 商品表
 *
 * @author cheer
 */
public interface MallProductService extends IService<MallProductEntity> {

    List<MallProductSimpleVO> listProductSimpleByIds(List<Long> ids);

}

