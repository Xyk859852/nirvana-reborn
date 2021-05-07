package com.phoenix.nirvana.admin.web.api;

import com.phoenix.nirvana.admin.web.api.dto.user.AdminUserCreateDTO;
import com.phoenix.nirvana.admin.web.api.dto.user.AdminUserPageDTO;
import com.phoenix.nirvana.admin.web.api.dto.user.AdminUserUpdateDTO;
import com.phoenix.nirvana.admin.web.api.vo.user.UserPageItemVO;
import com.phoenix.nirvana.common.vo.PageResult;

import java.util.List;

public interface AdminUserService {


    /**
     * 获取管理员登录列表
     *
     * @param adminUserPageDTO
     * @return
     */
    PageResult<UserPageItemVO> getUserPageList(AdminUserPageDTO adminUserPageDTO);


    /**
     * 新增管理员用户
     *
     * @param adminUserCreateDTO
     */
    void addAdminUser(AdminUserCreateDTO adminUserCreateDTO);

    /**
     * 修改管理员用户信息
     * @param adminUserUpdateDTO
     */
    void updateAdminUser(AdminUserUpdateDTO adminUserUpdateDTO);

    /**
     * 删除管理员用户
     * @param ids
     */
    void deleteAdminUser(List<Long> ids);



}
