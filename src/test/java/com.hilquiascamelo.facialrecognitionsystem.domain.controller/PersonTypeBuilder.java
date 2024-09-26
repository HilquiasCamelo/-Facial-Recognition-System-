package com.hilquiascamelo.facialrecognitionsystem.domain.controller;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.PersonTypeDto;

import java.util.Collections;
import java.util.List;

public class PersonTypeBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static PersonTypeDto getDto() {
        PersonTypeDto dto = new PersonTypeDto();
        dto.setId("1");
        return dto;
    }
}
