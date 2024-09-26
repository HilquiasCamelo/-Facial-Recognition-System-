package com.hilquiascamelo.facialrecognitionsystem.domain.mapper;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.AttendanceEventDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.AttendanceEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttendanceEventMapper extends EntityMapper<AttendanceEventDto, AttendanceEvent> {
}