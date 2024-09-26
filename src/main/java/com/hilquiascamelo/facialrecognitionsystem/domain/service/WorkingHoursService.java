package com.hilquiascamelo.facialrecognitionsystem.domain.service;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.WorkingHoursDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.mapper.WorkingHoursMapper;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.WorkingHours;
import com.hilquiascamelo.facialrecognitionsystem.domain.repository.WorkingHoursRepository;
import com.hilquiascamelo.facialrecognitionsystem.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class WorkingHoursService {
    private final WorkingHoursRepository repository;
    private final WorkingHoursMapper workingHoursMapper;

    public WorkingHoursService(WorkingHoursRepository repository, WorkingHoursMapper workingHoursMapper) {
        this.repository = repository;
        this.workingHoursMapper = workingHoursMapper;
    }

    public WorkingHoursDto save(WorkingHoursDto workingHoursDto) {
        WorkingHours entity = workingHoursMapper.toEntity(workingHoursDto);
        return workingHoursMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public WorkingHoursDto findById(Long id) {
        return workingHoursMapper.toDto(repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    public Page<WorkingHoursDto> findByCondition(WorkingHoursDto workingHoursDto, Pageable pageable) {
        Page<WorkingHours> entityPage = repository.findAll(pageable);
        List<WorkingHours> entities = entityPage.getContent();
        return new PageImpl<>(workingHoursMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public WorkingHoursDto update(WorkingHoursDto workingHoursDto, Long id) {
        WorkingHoursDto data = findById(id);
        WorkingHours entity = workingHoursMapper.toEntity(workingHoursDto);
        BeanUtils.copyProperties(data, entity);
        return save(workingHoursMapper.toDto(entity));
    }
}
