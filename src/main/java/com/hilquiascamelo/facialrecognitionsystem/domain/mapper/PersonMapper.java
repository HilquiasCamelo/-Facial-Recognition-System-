package com.hilquiascamelo.facialrecognitionsystem.domain.mapper;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.PersonDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper extends EntityMapper<PersonDto, Person> {
}