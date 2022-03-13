package cn.tangseng233.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author tangseng233
 * @Description
 * @Date 2022-03-13 17:50
 **/
@SpringCloudApplication  //开启了SpringCloud注解 里面包含了发现服务的注解
@EnableZuulProxy     //开启Zuul代理
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class,args);
    }
}
