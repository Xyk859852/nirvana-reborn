package com.phoenix.nirvana.admin.web.impl.service;

import com.phoenix.nirvana.admin.web.api.SysErrorCodeService;
import com.phoenix.nirvana.admin.web.api.dto.ErrorCodeAutoGenerateDTO;
import com.phoenix.nirvana.admin.web.api.vo.SysErrorCodeVO;
import com.phoenix.nirvana.admin.web.impl.convert.SysErrorCodeConvert;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysErrorCodeDO;
import com.phoenix.nirvana.admin.web.impl.mapper.SysErrorCodeMapper;
import com.phoenix.nirvana.common.util.CollectionUtils;
import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.error.code.errorcode.ErrorCodeTypeEnum;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@DubboService
public class SysErrorCodeServiceImpl implements SysErrorCodeService {

    @Autowired
    SysErrorCodeMapper errorCodeMapper;

    @Override
    public CommonResult<List<SysErrorCodeVO>> listErrorCodes(String group) {
        return CommonResult.success(SysErrorCodeConvert.INTERFACE.convert(errorCodeMapper.selectListByGroup(group)));
    }

    @Override
    public CommonResult<List<SysErrorCodeVO>> listErrorCodes(String group, Date minUpdateTime) {
        return CommonResult.success(SysErrorCodeConvert.INTERFACE.convert(errorCodeMapper.selectListByGroup(group, minUpdateTime)));
    }

    @Transactional
    @Override
    public CommonResult<Boolean> autoGenerateErrorCodes(List<ErrorCodeAutoGenerateDTO> autoGenerateDTOs) {
        if (!CollectionUtils.isEmpty(autoGenerateDTOs)) {
            List<SysErrorCodeDO> sysErrorCodeDOS = errorCodeMapper.selectListByCodes(CollectionUtils.convertSet(autoGenerateDTOs, ErrorCodeAutoGenerateDTO::getCode));
            Map<Integer, SysErrorCodeDO> errorCodeAutoGenerateDTOMap = CollectionUtils.convertMap(sysErrorCodeDOS, SysErrorCodeDO::getCode);
            autoGenerateDTOs.forEach(autoGenerateDTO -> {
                SysErrorCodeDO sysErrorCode = errorCodeAutoGenerateDTOMap.get(autoGenerateDTO.getCode());
                if (sysErrorCode == null) {
                    SysErrorCodeDO sysErrorCodeDO = SysErrorCodeConvert.INTERFACE.convert(autoGenerateDTO);
                    sysErrorCodeDO.setCreateTime(new Date());
                    sysErrorCodeDO.setUpdateTime(new Date());
                    sysErrorCodeDO.setType(ErrorCodeTypeEnum.AUTO_GENERATION.getType());
                    errorCodeMapper.insert(sysErrorCodeDO);
                    return;
                }
                if (!sysErrorCode.getType().equals(ErrorCodeTypeEnum.AUTO_GENERATION.getType())) {
                    return;
                }
                if (!sysErrorCode.getGroup().equals(autoGenerateDTO.getGroup())) {
                    return;
                }
                if (sysErrorCode.getMessage().equals(autoGenerateDTO.getMessage())) {
                    return;
                }
                errorCodeMapper.updateById(new SysErrorCodeDO().setId(sysErrorCode.getId()).setMessage(autoGenerateDTO.getMessage()));

            });
        }
        return CommonResult.success(true);
    }
}
