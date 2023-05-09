package com.phoenix.nirvana.service.system.rpc.dept;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.manager.dept.DepartmentManager;
import com.phoenix.nirvana.service.system.rpc.dept.domain.vo.DepartmentCascade;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DubboService
public class DepartmentRpcImpl implements DepartmentRpc {

    @Autowired
    DepartmentManager departmentManager;

    @Override
    public CommonResult<List<DepartmentCascade>> getDepartmentsCascade(Long userId) {
        return departmentManager.getDepartmentsCascade(userId);
    }
}
