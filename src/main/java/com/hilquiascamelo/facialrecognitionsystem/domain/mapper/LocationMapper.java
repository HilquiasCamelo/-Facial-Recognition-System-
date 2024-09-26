package com.hilquiascamelo.facialrecognitionsystem.domain.mapper;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.LocationDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper extends EntityMapper<LocationDto, Location> {
}