package com.phoenix.nirvana.admin.web.api;

import com.phoenix.nirvana.admin.web.api.bo.OnlineUserBO;
import com.phoenix.nirvana.common.vo.CommonResult;

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
