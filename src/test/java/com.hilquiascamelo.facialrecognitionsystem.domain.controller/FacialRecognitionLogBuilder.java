package com.hilquiascamelo.facialrecognitionsystem.domain.controller;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.FacialRecognitionLogDto;

import java.util.Collections;
import java.util.List;

public class FacialRecognitionLogBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static FacialRecognitionLogDto getDto() {
        FacialRecognitionLogDto dto = new FacialRecognitionLogDto();
        dto.setId("1");
        return dto;
    }
}
