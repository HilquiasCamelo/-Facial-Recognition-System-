package com.hilquiascamelo.facialrecognitionsystem.domain.controller;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.AttendanceEventDto;

import java.util.Collections;
import java.util.List;

public class AttendanceEventBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static AttendanceEventDto getDto() {
        AttendanceEventDto dto = new AttendanceEventDto();
        dto.setId("1");
        return dto;
    }
}
