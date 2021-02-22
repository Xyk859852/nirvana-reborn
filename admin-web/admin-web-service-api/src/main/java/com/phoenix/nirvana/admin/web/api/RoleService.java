package com.phoenix.nirvana.admin.web.api;

import com.phoenix.nirvana.admin.web.api.vo.department.DepartmentCascade;
import com.phoenix.nirvana.admin.web.api.vo.role.RoleCascade;

import java.util.List;

public interface RoleService {

    /**
     * 根据登录用户查询其子集角色
     *
     * @param userId
     * @return
     */
    List<RoleCascade> getRolesCascade(Long userId);
}
