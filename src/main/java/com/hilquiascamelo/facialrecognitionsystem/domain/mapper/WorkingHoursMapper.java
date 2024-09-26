package com.hilquiascamelo.facialrecognitionsystem.domain.mapper;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.WorkingHoursDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.WorkingHours;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkingHoursMapper extends EntityMapper<WorkingHoursDto, WorkingHours> {
}