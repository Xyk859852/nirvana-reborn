package com.phoenix.nirvana.service.system.rpc.admin;

import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.service.system.rpc.admin.domain.dto.ErrorCodeAutoGenerateDTO;
import com.phoenix.nirvana.service.system.rpc.admin.domain.vo.errorcode.SysErrorCodeVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Validated
public interface SysErrorCodeRpc {

    /**
     * 获取错误码数据列表
     *
     * @param group 错误码分组
     * @return
     */
    CommonResult<List<SysErrorCodeVO>> listErrorCodes(@NotNull(message = "ErrorCode group not null") String group);

    /**
     * 获得指定分组下的错误码列表
     *
     * @param group         错误码分组
     * @param minUpdateTime 最小更新时间，允许为空。
     *                      通过该参数，我们可以增量获取超过 minUpdateTime 时间的错误码
     * @return 错误码列表
     */
    CommonResult<List<SysErrorCodeVO>> listErrorCodes(@NotNull(message = "ErrorCode group not null") String group, Date minUpdateTime);

    /**
     * 添加 SysErrCode 对象
     * @param autoGenerateDTOs
     * @return
     */
    CommonResult<Boolean> autoGenerateErrorCodes(@Valid List<ErrorCodeAutoGenerateDTO> autoGenerateDTOs);
}
