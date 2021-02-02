package com.phoenix.nirvana.admin.web.impl.service;

import com.phoenix.nirvana.admin.web.api.UserService;
import com.phoenix.nirvana.admin.web.api.vo.UserVO;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysUserDO;
import com.phoenix.nirvana.admin.web.impl.mapper.SysUserMapper;
import com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import static com.phoenix.nirvana.admin.web.impl.enums.AdminWebCodeConstants.USER_IS_NULL;

@Service
@DubboService
public class UserServiceImpl implements UserService {

    @Autowired
    SysUserMapper sysUserMapper;


    public UserVO getUserById(Long id) {
        SysUserDO sysUserDO = sysUserMapper.selectById(id);
        if (sysUserDO == null) {
            throw ServiceExceptionUtil.exception(USER_IS_NULL.getCode());
        }
        UserVO userVO = new UserVO();
        userVO.setName(sysUserDO.getUserName());
        userVO.setPhone(sysUserDO.getPhone());

        return userVO;
    }


}
