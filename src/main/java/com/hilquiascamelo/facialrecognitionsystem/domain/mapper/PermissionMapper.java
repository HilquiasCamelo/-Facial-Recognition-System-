package com.hilquiascamelo.facialrecognitionsystem.domain.mapper;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.PermissionDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper extends EntityMapper<PermissionDto, Permission> {
}