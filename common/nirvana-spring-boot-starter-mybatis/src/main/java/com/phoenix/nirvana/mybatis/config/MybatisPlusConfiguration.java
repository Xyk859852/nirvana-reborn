package com.phoenix.nirvana.mybatis.config;

import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

public class MybatisPlusConfiguration {
    
    @Autowired
    DataSource dataSource;
    
    /**
     * 配置事务处理机制
     *
     * @return
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
    
    @Bean
    public ISqlInjector sqlInjector() {
        return new DefaultSqlInjector(); // MyBatis Plus 逻辑删除
    }
    
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor(); // MyBatis Plus 分页插件
    }
    
    
}
