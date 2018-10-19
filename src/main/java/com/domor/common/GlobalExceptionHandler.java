package com.domor.common;

import com.domor.utils.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.domor.model.Result;

/**
 * 全局异常处理
 * @author liyy
 * @since 2018/5/3
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result errorHandler(Exception ex) {
        logger.error("捕获到Exception异常",ex);
    	return Result.error("操作异常，请联系管理员");
    }
    
    /**
     * 拦截捕捉自定义异常 MyException
     * @param ex
     * @return
     */
    @ExceptionHandler(value = MyException.class)
    public Result myErrorHandler(MyException ex) {
        logger.error("MyException",ex.getMsg());
    	return Result.error(ex.getMsg());
    }

}