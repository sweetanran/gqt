package com.cheer.enums;

import java.util.Objects;

/**
 * @author cheer
 */
public enum AdminRoleEnum {

    ADMIN(1, "普通管理员"),
    YOUNG(2, "团省委管理员"),
    SHOP(3, "商家");

    private Integer code;
    private String message;

    AdminRoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String codeToName(Integer code) {
        for (AdminRoleEnum roleEnum : AdminRoleEnum.values()) {
            if (Objects.equals(roleEnum.getCode(), code)) {
                return roleEnum.name().toUpperCase();
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
