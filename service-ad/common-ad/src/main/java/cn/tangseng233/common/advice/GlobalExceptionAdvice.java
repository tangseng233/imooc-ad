package cn.tangseng233.common.advice;

import cn.tangseng233.common.exception.ApiException;
import cn.tangseng233.common.vo.CommonResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tangseng233
 * @Description
 * @Date 2022-03-13 21:46
 **/
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = ApiException.class)
    public CommonResultVO<Object> ApiExceptionHandler(HttpServletRequest request, ApiException e){
        CommonResultVO<Object> result = new CommonResultVO<>(-1, "服务器内部错误");
        result.setData(e.getMessage());
        return result;
    }

}
