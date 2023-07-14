package com.phoenix.nirvana.web.system.client.dept;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.dept.DepartmentRpc;
import com.phoenix.nirvana.service.system.rpc.dept.domain.vo.DepartmentCascade;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentRpcClient {

    @DubboReference
    DepartmentRpc departmentRpc;

    public List<DepartmentCascade> getDepartmentsCascade(Long userId) {
        CommonResult<List<DepartmentCascade>> result = departmentRpc.getDepartmentsCascade(userId);
        result.checkError();
        return result.getData();
    }

}
