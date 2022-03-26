package com.phoenix.nirvana.service.system.convert;

import com.phoenix.nirvana.service.system.dal.mysql.dataobject.SysDepartmentDO;
import com.phoenix.nirvana.service.system.rpc.admin.domain.vo.department.DepartmentCascade;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysDepartmentConvert {

    SysDepartmentConvert INTERFACE = Mappers.getMapper(SysDepartmentConvert.class);

    @Mappings({})
    DepartmentCascade convert(SysDepartmentDO departmentDO);

    @Mappings({})
    List<DepartmentCascade> convert(List<SysDepartmentDO> departmentDO);

}
