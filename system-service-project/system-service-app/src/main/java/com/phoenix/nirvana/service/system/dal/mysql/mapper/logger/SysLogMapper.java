package com.phoenix.nirvana.service.system.dal.mysql.mapper.logger;

import com.phoenix.nirvana.mybatis.core.mapper.BaseMapperX;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.logger.SysLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  系统日志表 Mapper 接口
 * </p>
 *
 * @author Xuyk
 * @since 2021-02-01
 */
@Mapper
public interface SysLogMapper extends BaseMapperX<SysLogDO> {

}
