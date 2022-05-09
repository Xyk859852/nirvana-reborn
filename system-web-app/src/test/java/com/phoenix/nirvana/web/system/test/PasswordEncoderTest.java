package com.phoenix.nirvana.web.system.test;

import com.phoenix.nirvana.web.system.SystemWebApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SystemWebApplication.class)
public class PasswordEncoderTest {

    @Resource
    private PasswordEncoder passwordEncoder;


    @Test
    public void test(){
        System.out.println(passwordEncoder.matches("123456",""));
    }
}
