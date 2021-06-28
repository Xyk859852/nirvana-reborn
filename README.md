> 本项目也是借鉴了一些Git上面一些传统项目结构，如有雷同，还请见谅，整体的项目结构我是参考了Git的onemall商城项目
>
> 这个项目主要做技术整合
>
> * 1、微服务技术选型以 Spring Cloud Alibaba 为中心。
> * 2、整合开发使用的一系列中间件比如：nacos，dubbo，mq 等
> * 3、主做后台服务开发，前端页面暂不开发，若是后面有人加入在考虑
> * 4、在进行整合技术框架时，我会对应相应的文章进行讲解
> * 5、在进行做整合时，会根据一些面试问题进行对应的开发指导，希望会对大部分人有帮助

## 技术栈

### 后端

| 框架                                                         | 说明                  | 版本                         |
| ------------------------------------------------------------ | --------------------- | ---------------------------- |
| [Spring Boot](https://spring.io/projects/spring-boot)        | 应用开发框架          | 2.2.5.RELEASE                |
| [Spring Cloud](https://spring.io/projects/spring-cloud)      | 微服务开发框架        | Hoxton.SR1                   |
| [Spring Cloud Alibaba](https://spring.io/projects/spring-cloud-alibaba#learn) | 微服务开发框架        | 2.2.1.RELEASE                |
| [Nacos](https://nacos.io/zh-cn/docs/quick-start.html)        | 注册中心&配置中心     | 2.2.1.RELEASE                |
| [MySQL](https://www.mysql.com/cn/)                           | 数据库服务器          | 5.6                          |
| [Druid](https://github.com/alibaba/druid)                    | JDBC 连接池、监控组件 | 1.1.16                       |
| [MyBatis](http://www.mybatis.org/mybatis-3/zh/index.html)    | 数据持久层框架        | 3.5.4                        |
| [MyBatis-Plus](https://mp.baomidou.com/)                     | Mybatis 增强工具包    | 3.3.2                        |
| [Redis](https://redis.io/)                                   | key-value 数据库      | 暂未引入，等压测后，部分模块 |
| [Redisson](https://github.com/redisson/redisson)             | Redis 客户端          | 暂未引入，等压测后，部分模块 |
| [Elasticsearch](https://www.elastic.co/cn/)                  | 分布式搜索引擎        | 6.7.1                        |
| [Dubbo](http://dubbo.apache.org/)                            | 分布式 RPC 服务框架   | 2.7.7                        |
| [RocketMQ](http://dubbo.apache.org/)                         | 消息中间件            | 2.1.0                        |
| [Seata](https://github.com/seata/seata)                      | 分布式事务中间件      | 1.1.0                        |
| [XXL-Job](http://www.xuxueli.com/xxl-job/)                   | 分布式任务调度平台    | 2.0.1                        |
| [springfox-swagger2](https://github.com/springfox/springfox/tree/master/springfox-swagger2) | API 文档              | 2.9.2                        |
| [swagger-bootstrap-ui](https://gitee.com/xiaoym/swagger-bootstrap-ui) | Swagger 增强 UI 实现  | 1.9.3                        |

未来考虑引入

- [ ] 服务保障 Sentinel
- [ ] 网关 Soul

后端项目，目前的项目结构如下：

```Java
[-] xxx
  ├──[-] xxx-application // 提供对外 HTTP API 。
  ├──[-] xxx-service-api // 提供 Dubbo 服务 API 。
  ├──[-] xxx-service-impl // 提供 Dubbo 服务 Service 实现。
```

