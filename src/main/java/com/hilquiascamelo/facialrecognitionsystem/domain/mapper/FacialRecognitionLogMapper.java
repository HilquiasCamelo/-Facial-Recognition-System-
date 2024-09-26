package com.hilquiascamelo.facialrecognitionsystem.domain.mapper;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.FacialRecognitionLogDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.FacialRecognitionLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FacialRecognitionLogMapper extends EntityMapper<FacialRecognitionLogDto, FacialRecognitionLog> {
}