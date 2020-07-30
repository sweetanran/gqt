package com.cheer.security;

import com.cheer.entity.MallAdminEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author cheer
 */
@Component
public class SpringSecurityUserInfoHolder implements UserInfoHolder {

    @Override
    public MallAdminEntity getAdmin() {
        return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAdmin();
    }

    @Override
    public Long getShopId() {
        return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getShopId();
    }
}
