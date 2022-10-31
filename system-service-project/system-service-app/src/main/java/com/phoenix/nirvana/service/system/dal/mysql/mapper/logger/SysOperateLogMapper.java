package com.phoenix.nirvana.service.system.dal.mysql.mapper.logger;

import com.phoenix.nirvana.mybatis.core.mapper.BaseMapperX;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.logger.SysOperateLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 操作日志记录 Mapper 接口
 * </p>
 *
 * @author xuyongkang
 * @since 2022-10-20
 */
@Mapper
public interface SysOperateLogMapper extends BaseMapperX<SysOperateLogDO> {

}
