package com.cheer.enums;

/**
 * @author cheer
 */
public enum ProductStatusEnum {

    PEND_REVIEW(1, "待审核"),
    PASSED(2, "已通过"),
    NOT_ACTIVATED(3, "未启用"),
    REMOVED(4, "已下架");

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
