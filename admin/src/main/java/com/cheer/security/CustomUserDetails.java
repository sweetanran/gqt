package com.cheer.security;

import com.cheer.entity.MallAdminEntity;
import com.cheer.enums.AdminRoleEnum;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * SpringSecurity需要的用户详情
 *
 * @author cheer
 */
public class CustomUserDetails implements UserDetails {

    @Getter
    private MallAdminEntity admin;

    @Getter
    private Long shopId;

    public CustomUserDetails(MallAdminEntity admin) {
        this.admin = admin;
    }

    public CustomUserDetails(MallAdminEntity admin, Long shopId) {
        this.admin = admin;
        this.shopId = shopId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + AdminRoleEnum.codeToName(admin.getRole())));
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    /**
     * 账户是否没有过期
     *
     * @return true：未过期 false：已过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否没被锁定/冻结
     *
     * @return true：未锁定/未冻结 false：已锁定/已冻结
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否没有过期
     *
     * @return true：未过期 false：已过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否可用（是否被删除）
     *
     * @return true：可以使用 false：不可以使用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
