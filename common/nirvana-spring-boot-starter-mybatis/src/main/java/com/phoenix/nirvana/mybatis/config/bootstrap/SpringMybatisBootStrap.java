package com.phoenix.nirvana.mybatis.config.bootstrap;

import com.phoenix.nirvana.mybatis.config.bootstrap.mapper.UserMapper;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMybatisBootStrap {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/mybatis/spring-config.xml");
        DefaultSqlSessionFactory sqlSessionFactoryBean = (DefaultSqlSessionFactory) context.getBean("sqlSessionFactory");
        System.out.println("sqlSessionFactoryBean:" + sqlSessionFactoryBean);
        SqlSessionTemplate sqlSessionTemplate = context.getBean("sessionTemplate", SqlSessionTemplate.class);
        System.out.println("sqlSessionTemplate:" + sqlSessionTemplate);
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        User user = userMapper.selectById(1);
        System.out.println("user：" + user);
        context.close();
    }
}
