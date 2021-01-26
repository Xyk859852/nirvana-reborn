package com.phoenix.nirvana.admin.web.application.controller;

import com.phoenix.nirvana.admin.web.api.UserService;
import com.phoenix.nirvana.admin.web.api.vo.UserVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("user")
public class UserController {

    @DubboReference
    UserService userService;

    @GetMapping("/getUserById/{id}")
    public UserVO getUSerById(@PathVariable("id") String id){
        return userService.getUserById(Long.valueOf(id));
    }


}
