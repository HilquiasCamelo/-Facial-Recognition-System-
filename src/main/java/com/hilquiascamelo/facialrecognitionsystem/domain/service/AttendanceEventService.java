package com.hilquiascamelo.facialrecognitionsystem.domain.service;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.AttendanceEventDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.mapper.AttendanceEventMapper;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.AttendanceEvent;
import com.hilquiascamelo.facialrecognitionsystem.domain.repository.AttendanceEventRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.hilquiascamelo.facialrecognitionsystem.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class AttendanceEventService {
    private final AttendanceEventRepository repository;
    private final AttendanceEventMapper attendanceEventMapper;

    public AttendanceEventService(AttendanceEventRepository repository, AttendanceEventMapper attendanceEventMapper) {
        this.repository = repository;
        this.attendanceEventMapper = attendanceEventMapper;
    }

    public AttendanceEventDto save(AttendanceEventDto attendanceEventDto) {
        AttendanceEvent entity = attendanceEventMapper.toEntity(attendanceEventDto);
        return attendanceEventMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public AttendanceEventDto findById(Long id) {
        return attendanceEventMapper.toDto(repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    public Page<AttendanceEventDto> findByCondition(AttendanceEventDto attendanceEventDto, Pageable pageable) {
        Page<AttendanceEvent> entityPage = repository.findAll(pageable);
        List<AttendanceEvent> entities = entityPage.getContent();
        return new PageImpl<>(attendanceEventMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public AttendanceEventDto update(AttendanceEventDto attendanceEventDto, Long id) {
        AttendanceEventDto data = findById(id);
        AttendanceEvent entity = attendanceEventMapper.toEntity(attendanceEventDto);
        BeanUtils.copyProperties(data, entity);
        return save(attendanceEventMapper.toDto(entity));
    }
}
