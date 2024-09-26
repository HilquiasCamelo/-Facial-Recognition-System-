package com.hilquiascamelo.facialrecognitionsystem.domain.service;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.LocationDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.mapper.LocationMapper;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.Location;
import com.hilquiascamelo.facialrecognitionsystem.domain.repository.LocationRepository;
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
public class LocationService {
    private final LocationRepository repository;
    private final LocationMapper locationMapper;

    public LocationService(LocationRepository repository, LocationMapper locationMapper) {
        this.repository = repository;
        this.locationMapper = locationMapper;
    }

    public LocationDto save(LocationDto locationDto) {
        Location entity = locationMapper.toEntity(locationDto);
        return locationMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public LocationDto findById(Long id) {
        return locationMapper.toDto(repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    public Page<LocationDto> findByCondition(LocationDto locationDto, Pageable pageable) {
        Page<Location> entityPage = repository.findAll(pageable);
        List<Location> entities = entityPage.getContent();
        return new PageImpl<>(locationMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public LocationDto update(LocationDto locationDto, Long id) {
        LocationDto data = findById(id);
        Location entity = locationMapper.toEntity(locationDto);
        BeanUtils.copyProperties(data, entity);
        return save(locationMapper.toDto(entity));
    }
}
