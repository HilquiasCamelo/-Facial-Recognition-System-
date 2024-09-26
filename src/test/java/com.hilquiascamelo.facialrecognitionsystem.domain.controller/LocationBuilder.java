package com.hilquiascamelo.facialrecognitionsystem.domain.controller;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.LocationDto;

import java.util.Collections;
import java.util.List;

public class LocationBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static LocationDto getDto() {
        LocationDto dto = new LocationDto();
        dto.setId("1");
        return dto;
    }
}
