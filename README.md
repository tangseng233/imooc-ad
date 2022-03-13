# 慕课广告系统项目实战笔记

## 项目结构



## Eureka

### 项目搭建

1. 新建 spring boot 项目

2. 引入 Netflix eureka server

3. 开启注解

   ```java
   @SpringBootApplication
   @EnableEurekaServer//开启eureka注解
   public class EurekaApplication {
       public static void main(String[] args) {
           SpringApplication.run(EurekaApplication.class,args);
       }
   }
   ```

4. 编写配置

   ```yml
   server:
     port: 8000  #服务端口
   
   spring:
     application:
       name: eureka  #服务名称
   #eureka配置
   eureka:
     instance:
       hostname: localhost #实例主机名，后面配置url或者集群可以用到
     client:  #客户端相关
       fetch-registry: false    #获取服务 单机环境false，集群用true
       register-with-eureka: false #把自己注册到eureka  单机环境false，集群用true
       service-url: #本实例注册连接
         defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
   ```

5. 启动即可看到注册中心。

## gateway

### 项目搭建

1. 新建 spring boot 项目

2. 引入 eureka client 与 zuul 依赖。

3. 开始注解

   ```java
   @SpringCloudApplication  //开启了SpringCloud注解 里面包含了发现服务的注解
   @EnableZuulProxy     //开启Zuul代理
   public class ZuulApplication {
       public static void main(String[] args) {
           SpringApplication.run(ZuulApplication.class,args);
       }
   }
   ```

4. 编写配置

   ```yml
   server:
     port: 9001
   spring:
     application:
       name: zuul
   eureka:
     client:
       service-url:
         defaultZone: http://localhost:8000/eureka/
   ```

5. 启动即可。

### 网关拦截

![zuul](https://image.tangseng233.cn/markdown/202203131822036.png)

上图列出各个 Filter 类型在什么时间段进行拦截执行。

**实现拦截的步骤**

1. 继承 **ZuulFilter** 类
2. 重写相应的方法即可
    - filterType() 标记该 Filter 是什么类型的 filter
    - filterOrder()  filter 执行顺序  0最优先
    - shouldFilter() 是否启用 Filter
    - run() filter 具体逻辑

**例子：统计一个请求的响应时间，并打印日志**

`pre` Filter 记录开始时间，`post` Filter 记录结束时间。

"pre" 拦截器记录请求到达时间

```java
@Component
public class LogPreFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
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
        //记录请求刚到达的时间
        long startTime = System.currentTimeMillis();

        //存放到request域中
        RequestContext context = RequestContext.getCurrentContext();
        context.put("startTime",startTime);

        return null;
    }
}
```

"post" 拦截器记录响应完成时间

```java
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
```

