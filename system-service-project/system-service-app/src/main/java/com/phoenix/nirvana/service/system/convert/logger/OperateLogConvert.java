package com.phoenix.nirvana.service.system.convert.logger;

import com.phoenix.nirvana.service.system.dal.mysql.dataobject.logger.SysOperateLogDO;
import com.phoenix.nirvana.service.system.rpc.logger.domain.dto.OperateLogCreateReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperateLogConvert {

    OperateLogConvert INSTANCE = Mappers.getMapper(OperateLogConvert.class);

    SysOperateLogDO convert(OperateLogCreateReqDTO bean);

}
