package com.phoenix.nirvana.service.system.dal.mysql.mapper.config;

import com.phoenix.nirvana.service.system.dal.mysql.dataobject.config.SysConfigDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 参数配置表 Mapper 接口
 * </p>
 *
 * @author xuyongkang
 * @since 2022-10-19
 */
@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfigDO> {

}
