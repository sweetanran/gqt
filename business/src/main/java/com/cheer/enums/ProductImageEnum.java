package com.cheer.enums;

/**
 * @author cheer
 */
public enum ProductImageEnum {

    THUMBNAIL(1, "缩略图"),
    DISPLAY(2, "展示图"),
    DESC(3, "详情图");

    private Integer code;
    private String message;

    ProductImageEnum(Integer code, String message) {
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
