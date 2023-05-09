package com.phoenix.nirvana.web.system.client.role;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.service.system.rpc.role.RoleRpc;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.AddRoleDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.RolePageDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.dto.UpdateRoleDTO;
import com.phoenix.nirvana.service.system.rpc.role.domain.vo.RoleCascade;
import com.phoenix.nirvana.service.system.rpc.role.domain.vo.RolePageItemVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleRpcClient {

    @DubboReference
    RoleRpc roleRpc;

    public List<RoleCascade> getRolesCascade(Long id) {
        CommonResult<List<RoleCascade>> result = roleRpc.getRolesCascade(id);
        result.checkError();
        return result.getData();
    }

    public PageResult<RolePageItemVO> getRolePageList(RolePageDTO rolePageDTO) {
        CommonResult<PageResult<RolePageItemVO>> result = roleRpc.getRolePageList(rolePageDTO);
        result.checkError();
        return result.getData();
    }

    public Boolean createRole(AddRoleDTO addRoleDTO, Long userId) {
        CommonResult<Boolean> result = roleRpc.createRole(addRoleDTO, userId);
        result.checkError();
        return result.getData();
    }

    public Boolean updateRole(UpdateRoleDTO updateRoleDTO) {
        CommonResult<Boolean> result = roleRpc.updateRole(updateRoleDTO);
        result.checkError();
        return result.getData();
    }

    public Boolean deleteRole(List<Long> ids) {
        CommonResult<Boolean> result = roleRpc.deleteRole(ids);
        result.checkError();
        return result.getData();
    }
}

