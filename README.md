## 个人博客 [小许的Java驿站](https://blog.csdn.net/qq_38100149?type=blog) 欢迎大家查阅指导



> 这个项目主要做技术整合
> 
>* 1、微服务技术选型以 Spring Cloud Alibaba 为中心。
> * 2、整合开发使用的一系列中间件比如：nacos，dubbo，mq 等
> * 3、在进行整合技术框架时，我会对应相应的文章进行讲解，目前已有：SpringCloud，Eureka，SpringMVC，Mybatis等文章全部可以在  [小许的Java驿站](https://blog.csdn.net/qq_38100149?type=blog) 里面找到。
> * 4、在进行做整合时，会根据一些面试问题进行对应的开发指导，希望会对大部分人有帮助

## 技术栈

### 后端

| 框架                                                         | 说明        | 版本             |
| ------------------------------------------------------------ | ----------- |----------------|
| [Spring Boot](https://spring.io/projects/spring-boot)        | 应用开发框架    | 2.6.8          |
| [Spring Cloud](https://spring.io/projects/spring-cloud)      | 微服务开发框架   | 2021.0.1       |
| [Spring Cloud Alibaba](https://spring.io/projects/spring-cloud-alibaba#learn) | 微服务开发框架   | 2021.0.1.0     |
| [Spring Cloud Gateway](https://spring.io/projects/spring-cloud) | 微服务网关 | 2021.0.1       |
| [Nacos](https://nacos.io/zh-cn/docs/quick-start.html)        | 注册中心&配置中心 | 2.0            |
| [MySQL](https://www.mysql.com/cn/)                           | 数据库服务器    | 5.6            |
| [Druid](https://github.com/alibaba/druid)                    | JDBC 连接池、监控组件 | 1.1.16         |
| [MyBatis](http://www.mybatis.org/mybatis-3/zh/index.html)    | 数据持久层框架   | 3.5.9          |
| [MyBatis-Plus](https://mp.baomidou.com/)                     | Mybatis 增强工具包 | 3.5.0          |
| [Redis](https://redis.io/)                                   | key-value 数据库 | 5.0            |
| [Redisson](https://github.com/redisson/redisson)             | Redis 客户端 | 3.17.3         |
| [Elasticsearch](https://www.elastic.co/cn/)                  | 分布式搜索引擎   | 暂未引入，等压测后，部分模块 |
| [Dubbo](http://dubbo.apache.org/)                            | 分布式 RPC 服务框架 | 3.0.7          |
| [RocketMQ](http://dubbo.apache.org/)                         | 消息中间件     | 4.9.2          |
| [Seata](https://github.com/seata/seata)                      | 分布式事务中间件  | 暂未引入，等压测后，部分模块 |
| [XXL-Job](http://www.xuxueli.com/xxl-job/)                   | 分布式任务调度平台 | 暂未引入，等压测后，部分模块 |
| [swagger2](https://github.com/springfox/springfox/tree/master/springfox-swagger2) | API 文档    | 1.6.6          |
| [knife4j](https://gitee.com/xiaoym/knife4j.git) | Swagger 增强 UI 实现 | 3.0.3          |

未来考虑引入

- [ ] 服务保障 Sentinel
- [ ] 网关 Soul

## 前端

前端代码 [传送门](https://gitee.com/xu983364/nirvana-reborn-vue-ui.git)

| 框架 | 说明 |  版本 |
| --- | --- | --- |
| [Vue](https://cn.vuejs.org/index.html) | JavaScript 框架 | 2.5.17 |
| [Vue Ant Design](https://ant.design/docs/react/introduce-cn) | 后台前端解决方案 | - |



