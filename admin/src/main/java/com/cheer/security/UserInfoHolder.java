package com.cheer.security;


import com.cheer.entity.MallAdminEntity;

/**
 * @author cheer
 */
public interface UserInfoHolder {

    MallAdminEntity getAdmin();

    Long getShopId();

}
