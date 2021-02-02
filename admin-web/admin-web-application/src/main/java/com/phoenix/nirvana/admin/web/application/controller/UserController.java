package com.phoenix.nirvana.admin.web.application.controller;

import com.phoenix.nirvana.admin.security.annotaion.AnonymousAccess;
import com.phoenix.nirvana.admin.web.api.SysErrorCodeService;
import com.phoenix.nirvana.admin.web.api.UserService;
import com.phoenix.nirvana.admin.web.api.vo.SysErrorCodeVO;
import com.phoenix.nirvana.admin.web.api.vo.UserVO;
import com.phoenix.nirvana.common.vo.CommonResult;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @DubboReference
    UserService userService;

    @DubboReference
    SysErrorCodeService errorCodeService;

    @AnonymousAccess
    @GetMapping("/getUserById/{id}")
    public UserVO getUSerById(@PathVariable("id") String id){
//        CommonResult<List<SysErrorCodeVO>> commonResult = errorCodeService.listErrorCodes(null);
//        CommonResult<Boolean> autoGenerateErrorCodesResult = errorCodeService.autoGenerateErrorCodes(null);
        return userService.getUserById(Long.valueOf(id));
    }


}

