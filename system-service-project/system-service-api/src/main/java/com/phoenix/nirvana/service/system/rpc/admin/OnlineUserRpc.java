package com.phoenix.nirvana.service.system.rpc.admin;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.admin.domain.bo.OnlineUserBO;

public interface OnlineUserRpc {

    /**
     * 根据token 获取当前用户
     * @param token
     * @return
     */
    CommonResult<OnlineUserBO> getOnlineUserByToken(String token);

    /**
     * 根据token 获取当前用户
     * @param userName
     * @return
     */
    CommonResult<OnlineUserBO> getOnlineUserByUserName(String userName);
}
