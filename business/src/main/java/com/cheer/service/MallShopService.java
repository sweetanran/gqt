package com.cheer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cheer.entity.MallShopEntity;

/**
 * 商户表
 *
 * @author cheer
 */
public interface MallShopService extends IService<MallShopEntity> {

    MallShopEntity getByPhone(String phone);

}

