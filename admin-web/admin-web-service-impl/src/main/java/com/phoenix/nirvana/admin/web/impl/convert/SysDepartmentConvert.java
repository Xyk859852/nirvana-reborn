package com.phoenix.nirvana.admin.web.impl.convert;

import com.phoenix.nirvana.admin.web.api.vo.department.DepartmentCascade;
import com.phoenix.nirvana.admin.web.impl.dataobject.SysDepartmentDO;
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
