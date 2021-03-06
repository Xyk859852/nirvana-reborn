/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.phoenix.nirvana.admin.security.service;

import com.phoenix.nirvana.admin.security.bean.LoginProperties;
import com.phoenix.nirvana.admin.security.bo.SecurityUserBO;
import com.phoenix.nirvana.admin.web.api.OnlineUserService;
import com.phoenix.nirvana.admin.web.api.bo.OnlineUserBO;
import com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Zheng Jie
 * @date 2018-11-22
 */
@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    LoginProperties loginProperties;

    @DubboReference
    OnlineUserService onlineUserService;

    public void setEnableCache(boolean enableCache) {
        this.loginProperties.setCacheEnable(enableCache);
    }

    /**
     * 用户信息缓存
     *
     * @see {@link }
     */

    @Override
    public SecurityUserBO loadUserByUsername(String username) {
        boolean searchDb = true;
        SecurityUserBO jwtUserDto = null;
//        if (loginProperties.isCacheEnable() && userDtoCache.containsKey(username)) {
//            jwtUserDto = userDtoCache.get(username);
//            searchDb = false;
//        }
        if (searchDb) {
            OnlineUserBO user = onlineUserService.getOnlineUserByUserName(username);
            if (user == null) {
                throw new UsernameNotFoundException("");
            } else {
                if (user.getIsEnable()) {
                    throw ServiceExceptionUtil.exception(100011);
                }
                jwtUserDto = new SecurityUserBO().setOnlineUserBO(user);
//                userDtoCache.put(username, jwtUserDto);
            }
        }
        return jwtUserDto;
    }
}
