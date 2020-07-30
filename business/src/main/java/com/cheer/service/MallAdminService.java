package com.cheer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cheer.entity.MallAdminEntity;


/**
 * 管理员表
 *
 * @author cheer
 */
public interface MallAdminService extends IService<MallAdminEntity> {

    MallAdminEntity getByUsername(String username);
}

