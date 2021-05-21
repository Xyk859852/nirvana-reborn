package com.phoenix.nirvana.admin.web.api.admin;

import com.phoenix.nirvana.admin.web.api.admin.domain.vo.department.DepartmentCascade;

import java.util.List;

public interface DepartmentService {

    /**
     * 根据登录用户查询其子集部门
     *
     * @param userId
     * @return
     */
    List<DepartmentCascade> getDepartmentsCascade(Long userId);
}
