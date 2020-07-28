package com.cheer.exception;

import com.cheer.common.Result;
import com.cheer.common.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 异常统一处理
 *
 * @author cheer
 */
@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理 ServiceException 异常
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handleServiceException(ServiceException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * SpringMVC 参数不正确
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public Result missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        return Result.error(ResultEnum.BAD_REQUEST);
    }

    /**
     * Validator 参数不正确
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseBody
    public Result handleArgumentException(Exception e) {
        String message;
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        } else {
            message = e.getMessage();
        }
        return Result.error(ResultEnum.BAD_REQUEST.getCode(), message);
    }

    /**
     * 处理其它 Exception 异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e) {
        return Result.error(ResultEnum.SERVER_ERROR);
    }

}
