package com.cheer.exception;


import com.cheer.common.ResultEnum;

/**
 * @author cheer
 */
public class ServiceException extends RuntimeException {

    private Integer code;

    public ServiceException(String message) {
        super(message);
        this.code = ResultEnum.SERVER_ERROR.getCode();
    }

    public ServiceException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public ServiceException(ResultEnum resultEnum, String message) {
        super(message);
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
