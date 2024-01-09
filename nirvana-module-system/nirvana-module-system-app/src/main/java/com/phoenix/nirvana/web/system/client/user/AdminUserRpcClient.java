package com.phoenix.nirvana.web.system.client.user;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.common.vo.PageResult;
import com.phoenix.nirvana.service.system.rpc.user.AdminUserRpc;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserCreateDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserPageDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.dto.AdminUserUpdateDTO;
import com.phoenix.nirvana.service.system.rpc.user.domain.vo.AdminUserPageItemVO;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminUserRpcClient {

    @DubboReference
    AdminUserRpc adminUserRpc;

    public PageResult<AdminUserPageItemVO> getUserPageList(AdminUserPageDTO adminUserPageDTO) {
        RpcContext.getServiceContext().setAttachment(CommonConstants.TAG_KEY,"tag1");
        CommonResult<PageResult<AdminUserPageItemVO>> userPageList = adminUserRpc.getUserPageList(adminUserPageDTO);
        userPageList.checkError();
        return userPageList.getData();
    }

    public Boolean createAdminUser(AdminUserCreateDTO adminUserCreateDTO) {
        CommonResult<Boolean> commonResult = adminUserRpc.createAdminUser(adminUserCreateDTO);
        commonResult.checkError();
        return commonResult.getData();
    }

    public Boolean updateAdminUser(AdminUserUpdateDTO adminUserUpdateDTO) {
        CommonResult<Boolean> commonResult = adminUserRpc.updateAdminUser(adminUserUpdateDTO);
        commonResult.checkError();
        return commonResult.getData();
    }

    public Boolean deleteAdminUser(List<Long> ids) {
        CommonResult<Boolean> commonResult = adminUserRpc.deleteAdminUser(ids);
        commonResult.checkError();
        return commonResult.getData();
    }

}
