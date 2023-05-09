package com.phoenix.nirvana.service.system.service.config.impl;

import com.phoenix.nirvana.service.system.dal.mysql.dataobject.config.SysConfigDO;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.config.SysConfigMapper;
import com.phoenix.nirvana.service.system.service.config.SysConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 参数配置表 服务实现类
 * </p>
 *
 * @author xuyongkang
 * @since 2022-10-19
 */
@Service
@DubboService
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfigDO> implements SysConfigService {

}


