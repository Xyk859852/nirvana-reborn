package com.phoenix.nirvana.admin.web.api.admin;

import com.phoenix.nirvana.admin.web.api.admin.domain.bo.OnlineUserBO;

public interface OnlineUserService {

    /**
     * 根据token 获取当前用户
     * @param token
     * @return
     */
    OnlineUserBO getOnlineUserByToken(String token);

    /**
     * 根据token 获取当前用户
     * @param userName
     * @return
     */
    OnlineUserBO getOnlineUserByUserName(String userName);
}
