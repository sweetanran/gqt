package com.cheer.security;

import com.cheer.enums.AdminRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author cheer
 */
@Component
public class PermissionValidator {

    @Autowired
    private UserInfoHolder userInfoHolder;

    public boolean isAdmin() {
        return userInfoHolder.getAdmin().getRole().equals(AdminRoleEnum.ADMIN.getCode()) ||
                userInfoHolder.getAdmin().getRole().equals(AdminRoleEnum.YOUNG.getCode());
    }

    public boolean isShop() {
        return userInfoHolder.getAdmin().getRole().equals(AdminRoleEnum.SHOP.getCode());
    }

}
