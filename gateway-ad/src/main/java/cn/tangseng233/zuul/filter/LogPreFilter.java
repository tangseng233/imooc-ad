package cn.tangseng233.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @author tangseng233
 * @Description
 * @Date 2022-03-13 18:28
 **/
@Component
public class LogPreFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //标记该Filter是什么类型的
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //filter 执行顺序  0最优先
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //根据条件是否启用
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //filter逻辑
        //记录请求刚到达的时间
        long startTime = System.currentTimeMillis();

        //存放到request域中
        RequestContext context = RequestContext.getCurrentContext();
        context.put("startTime",startTime);

        return null;
    }
}
