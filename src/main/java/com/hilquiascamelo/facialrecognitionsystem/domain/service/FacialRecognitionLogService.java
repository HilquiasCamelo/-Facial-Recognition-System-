package com.hilquiascamelo.facialrecognitionsystem.domain.service;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.FacialRecognitionLogDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.mapper.FacialRecognitionLogMapper;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.FacialRecognitionLog;
import com.hilquiascamelo.facialrecognitionsystem.domain.repository.FacialRecognitionLogRepository;
import com.hilquiascamelo.facialrecognitionsystem.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
public class FacialRecognitionLogService {
    private final FacialRecognitionLogRepository repository;
    private final FacialRecognitionLogMapper facialRecognitionLogMapper;

    public FacialRecognitionLogService(FacialRecognitionLogRepository repository, FacialRecognitionLogMapper facialRecognitionLogMapper) {
        this.repository = repository;
        this.facialRecognitionLogMapper = facialRecognitionLogMapper;
    }

    public FacialRecognitionLogDto save(FacialRecognitionLogDto facialRecognitionLogDto) {
        FacialRecognitionLog entity = facialRecognitionLogMapper.toEntity(facialRecognitionLogDto);
        return facialRecognitionLogMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public FacialRecognitionLogDto findById(Long id) {
        return facialRecognitionLogMapper.toDto(repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    public Page<FacialRecognitionLogDto> findByCondition(FacialRecognitionLogDto facialRecognitionLogDto, Pageable pageable) {
        Page<FacialRecognitionLog> entityPage = repository.findAll(pageable);
        List<FacialRecognitionLog> entities = entityPage.getContent();
        return new PageImpl<>(facialRecognitionLogMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public FacialRecognitionLogDto update(FacialRecognitionLogDto facialRecognitionLogDto, Long id) {
        FacialRecognitionLogDto data = findById(id);
        FacialRecognitionLog entity = facialRecognitionLogMapper.toEntity(facialRecognitionLogDto);
        BeanUtils.copyProperties(data, entity);
        return save(facialRecognitionLogMapper.toDto(entity));
    }
}
