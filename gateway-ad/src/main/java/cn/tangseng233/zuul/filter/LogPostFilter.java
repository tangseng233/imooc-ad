package cn.tangseng233.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @author tangseng233
 * @Description
 * @Date 2022-03-13 18:33
 **/
@Component
@Slf4j
public class LogPostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        //从域中获取到达时间
        RequestContext context = RequestContext.getCurrentContext();

        String uri = context.getRequest().getRequestURI();
        long startTime = (long) context.get("startTime");
        long during = (System.currentTimeMillis() - startTime) / 1000;

        log.info("请求:{}, 用时:{}ms",uri,during);

        return null;
    }
}
