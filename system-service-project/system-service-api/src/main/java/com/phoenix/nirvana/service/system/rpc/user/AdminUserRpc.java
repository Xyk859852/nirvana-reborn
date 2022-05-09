package com.phoenix.nirvana.service.system.rpc.user;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserCreateDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserPageDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserUpdateDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.vo.AdminUserPageItemVO;

import java.util.List;

public interface AdminUserRpc {


    /**
     * 获取管理员登录列表
     *
     * @param adminUserPageDTO
     * @return
     */
    CommonResult<PageResult<AdminUserPageItemVO>> getUserPageList(AdminUserPageDTO adminUserPageDTO);


    /**
     * 新增管理员用户
     *
     * @param adminUserCreateDTO
     */
    CommonResult<Boolean> createAdminUser(AdminUserCreateDTO adminUserCreateDTO);

    /**
     * 修改管理员用户信息
     * @param adminUserUpdateDTO
     */
    CommonResult<Boolean> updateAdminUser(AdminUserUpdateDTO adminUserUpdateDTO);

    /**
     * 删除管理员用户
     * @param ids
     */
    CommonResult<Boolean> deleteAdminUser(List<Long> ids);



}
