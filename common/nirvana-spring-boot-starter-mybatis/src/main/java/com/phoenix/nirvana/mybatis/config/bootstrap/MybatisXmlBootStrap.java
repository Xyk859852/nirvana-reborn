package com.phoenix.nirvana.mybatis.config.bootstrap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

import java.io.Reader;
import java.util.Properties;

/**
 * properties -> xml
 * <p>
 * XmlConfigBuilder
 * <p>
 * org.apache.ibatis.session.Configuration
 * <p>
 * org.apache.ibatis.session.SqlSessionFactoryBuilder
 * org.apache.ibatis.session.SqlSessionFactory
 * org.apache.ibatis.session.SqlSession
 * <p>
 * <p>
 * mybatis xml 配置讲解
 */
public class MybatisXmlBootStrap {


    public static void main(String[] args) throws Exception {
        ResourceLoader resourceLoader = new DefaultResourceLoader();

        Resource resource = resourceLoader.getResource("classpath:/mybatis/mybatis-config.xml");
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

        Reader reader = encodedResource.getReader();
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(reader, "dev", new Properties());

        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = sqlSession.selectOne("User.selectById", 1);
        System.out.println(user);
        sqlSession.close();


    }

}
