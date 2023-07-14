package com.phoenix.nirvana.service.system.manager.dept;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.dept.domain.vo.DepartmentCascade;
import com.phoenix.nirvana.service.system.service.dept.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentManager {

    @Autowired
    DepartmentService departmentService;

    public CommonResult<List<DepartmentCascade>> getDepartmentsCascade(Long userId) {
        return CommonResult.success(departmentService.getDepartmentsCascade(userId));
    }
}
