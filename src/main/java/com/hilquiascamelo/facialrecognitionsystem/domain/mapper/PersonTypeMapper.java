package com.hilquiascamelo.facialrecognitionsystem.domain.mapper;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.PersonTypeDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.PersonType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonTypeMapper extends EntityMapper<PersonTypeDto, PersonType> {
}