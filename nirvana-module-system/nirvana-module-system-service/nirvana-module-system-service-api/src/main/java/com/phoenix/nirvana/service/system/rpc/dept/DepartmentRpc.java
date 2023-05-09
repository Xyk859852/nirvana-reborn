package com.phoenix.nirvana.service.system.rpc.dept;


import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.dept.domain.vo.DepartmentCascade;

import java.util.List;

public interface DepartmentRpc {

    /**
     * 根据登录用户查询其子集部门
     *
     * @param userId
     * @return
     */
    CommonResult<List<DepartmentCascade>> getDepartmentsCascade(Long userId);
}
