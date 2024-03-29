package com.phoenix.nirvana.service.system.test;

import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.phoenix.nirvana.service.system.SystemServiceApplication;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.dept.SysPostDO;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.dict.SysDictDO;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.errorcode.SysErrorCodeDO;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysPermissionDO;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysRoleDO;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.permission.SysRolePermissionDO;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.tenant.SysTenantDO;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.tenant.SysTenantPackageDO;
import com.phoenix.nirvana.service.system.dal.mysql.dataobject.user.SysUserDO;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.dept.SysPostMapper;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.errorcode.SysErrorCodeMapper;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.permission.SysPermissionMapper;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.permission.SysRoleMapper;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.permission.SysRolePermissionMapper;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.tenant.SysTenantMapper;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.tenant.SysTenantPackageMapper;
import com.phoenix.nirvana.service.system.dal.mysql.mapper.user.SysUserMapper;
import com.phoenix.nirvana.service.system.service.logger.OperateLogService;
import com.phoenix.nirvana.tenant.core.context.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = SystemServiceApplication.class)
public class TenantMybatisTest {

    @Autowired
    SysUserMapper userMapper;

    @Autowired
    SysRoleMapper roleMapper;

    @Autowired
    SysRolePermissionMapper rolePermissionMapper;

    @Autowired
    SysPermissionMapper permissionMapper;

    @Autowired
    SysPostMapper postMapper;

    @Autowired
    SysErrorCodeMapper errorCodeMapper;

    @Autowired
    SysTenantMapper tenantMapper;

    @Autowired
    SysTenantPackageMapper tenantPackageMapper;

    @Autowired
    OperateLogService operateLogService;


    @Test
    public void test() {
        TenantContextHolder.setTenantId(0l);
        List<SysUserDO> userList = userMapper.selectList();
        log.info("userList:{}", userList);

        List<SysRoleDO> roleList = roleMapper.selectList();
        log.info("roleList:{}", roleList);

        List<SysRolePermissionDO> sysRolePermissionList = rolePermissionMapper.selectList();
        log.info("sysRolePermissionList:{}", sysRolePermissionList);

        List<SysPermissionDO> sysPermissionList = permissionMapper.selectList();
        log.info("sysPermissionList:{}", sysPermissionList);

        List<SysPostDO> sysPostList = postMapper.selectList();
        log.info("sysPostList:{}", sysPostList);

        List<SysErrorCodeDO> sysErrorCodeList = errorCodeMapper.selectList();
        log.info("sysErrorCodeList:{}", sysErrorCodeList);

        List<SysTenantDO> tenantList = tenantMapper.selectList();
        log.info("tenantList:{}", tenantList);

        List<SysTenantPackageDO> tenantPackageList = tenantPackageMapper.selectList();
        log.info("tenantPackageList:{}", tenantPackageList);
    }

    @Test
    public void test2() {
        operateLogService.getById(123);
    }


    @Test
    public void test3() {
        SysDictDO dictDO1 = new SysDictDO();
        dictDO1.setName("123");
        dictDO1.setPid(0l);
        dictDO1.setCreator(0l);
        dictDO1.setType(0);
        SysDictDO dictDO2 = new SysDictDO();
        dictDO2.setName("123");
        dictDO2.setPid(0l);
        dictDO2.setCreator(0l);
        dictDO2.setType(0);
        SysDictDO dictDO3 = new SysDictDO();
        dictDO3.setName("123");
        dictDO3.setPid(0l);
        dictDO3.setCreator(0l);
        dictDO3.setType(0);
        Db.saveBatch(Arrays.asList(dictDO1, dictDO2, dictDO3));

    }

}
