package com.hilquiascamelo.facialrecognitionsystem.domain.controller;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.WorkingHoursDto;

import java.util.Collections;
import java.util.List;

public class WorkingHoursBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static WorkingHoursDto getDto() {
        WorkingHoursDto dto = new WorkingHoursDto();
        dto.setId("1");
        return dto;
    }
}
