package cn.tangseng233.common.advice;

import cn.tangseng233.common.anno.IgnoreCommonResultAdvice;
import cn.tangseng233.common.vo.CommonResultVO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author tangseng233
 * @Description
 * @Date 2022-03-13 21:11
 **/
@RestControllerAdvice
public class CommonResultAdvice implements ResponseBodyAdvice<Object> {

    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        //是否对ResponseBody进行增强
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreCommonResultAdvice.class)) {
            return false;
        }
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreCommonResultAdvice.class)) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        //写入Response前的操作
        CommonResultVO<Object> resultVO = new CommonResultVO<>(200, "成功");

        if (o == null) {
            return resultVO;
        } else if (o instanceof CommonResultVO) {
            return o;
        }else{
            resultVO.setData(o);
            return resultVO;
        }
    }
}
