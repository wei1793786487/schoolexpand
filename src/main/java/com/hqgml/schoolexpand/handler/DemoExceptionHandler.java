package com.hqgml.schoolexpand.handler;

import com.hqgml.schoolexpand.exception.JsonException;
import com.hqgml.schoolexpand.pojo.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * 统一异常处理
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-10-02 21:26
 */
@ControllerAdvice
@Slf4j
public class DemoExceptionHandler {

    /**
     * 统一 json 异常处理
     *
     * @param exception JsonException
     * @return 统一返回 json 格式
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResponse ErrorHandler(Exception exception) throws Exception {
       if (exception instanceof  JsonException){
           return ApiResponse.ofException((JsonException)exception);
       }else {
           throw exception;
       }
    }


}
