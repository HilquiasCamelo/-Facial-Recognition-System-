package com.hilquiascamelo.facialrecognitionsystem.domain.controller;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.PermissionDto;

import java.util.Collections;
import java.util.List;

public class PermissionBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static PermissionDto getDto() {
        PermissionDto dto = new PermissionDto();
        dto.setId("1");
        return dto;
    }
}
