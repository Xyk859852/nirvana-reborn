package com.phoenix.nirvana.service.system.convert.dept;

import com.phoenix.nirvana.service.system.dal.mysql.dataobject.dept.SysDepartmentDO;
import com.phoenix.nirvana.service.system.rpc.dept.domain.vo.DepartmentCascade;
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
