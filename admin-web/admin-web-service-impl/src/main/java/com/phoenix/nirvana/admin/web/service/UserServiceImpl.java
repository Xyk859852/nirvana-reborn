package com.phoenix.nirvana.admin.web.service;

import com.phoenix.nirvana.admin.web.api.UserService;
import com.phoenix.nirvana.admin.web.api.vo.UserVO;
import com.phoenix.nirvana.admin.web.dataobject.SysUser;
import com.phoenix.nirvana.admin.web.mapper.SysUserMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DubboService
public class UserServiceImpl implements UserService {

    @Autowired
    SysUserMapper sysUserMapper;


    public UserVO getUserById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        userVO.setName(sysUser.getUserName());
        userVO.setPhone(sysUser.getPhone());
        return userVO;
    }


}
