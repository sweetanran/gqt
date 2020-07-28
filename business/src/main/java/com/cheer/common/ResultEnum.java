package com.cheer.common;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 业务返回码
 *
 * @author cheer
 */
public enum ResultEnum {

    // ========== 基础返回码 ==========
    SUCCESS(200, "请求成功"),
    BAD_REQUEST(400, "参数不合法"),
    UNAUTHORIZED(401, "用户未登录"),
    FORBIDDEN(403, "用户没有执行该请求的权限"),
    NOT_FOUND(404, "内容不存在"),
    SERVER_ERROR(500, "请求异常");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("message", message)
                .toString();
    }
}
