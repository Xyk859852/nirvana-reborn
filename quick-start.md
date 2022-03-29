# nirvana-reborn启动文档



- 启动时本地需要的组件

  - mysql，对应的初始化sql在 doc目录下
  - nacos，对应的nacos-server也在对应的doc目录下
  - redis，可以参考 [redis-菜鸟教程](https://www.runoob.com/redis/redis-install.html)进行安装

- 启动项目步骤

  - 本项目是微服务架构，所以有对应的provider与consumer两个服务启动才可以正常运行，以管理平台模块启动为例

  - ```Java
    [-] system-service-project
      ├──[-] system-service-api // 提供 Dubbo 服务 API 。
      ├──[-] system-service-app // 提供 Dubbo 服务 Service 实现。
    [-] system-web-app // web 暴露前端的接口
      
    以上引用是 system-web-app 依赖了 system-service-api consumer服务API所以在启动的时候会去找对应的provider生产者system-service-app的实现，所以我们需要先把 system-service-app 启动起来，然后再去启动对应的 system-web-app 服务，其具体的依赖包还需要针对当前服务依赖包为准。一般来说，需要先启动 xxx-service-app（provider）在启动 xxx-web-app（consumer）。如有问题可以及时提Issues，我们都会去看的
     
    
    ```
