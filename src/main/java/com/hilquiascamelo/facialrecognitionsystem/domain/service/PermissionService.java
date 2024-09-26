package com.hilquiascamelo.facialrecognitionsystem.domain.service;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.PermissionDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.mapper.PermissionMapper;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.Permission;
import com.hilquiascamelo.facialrecognitionsystem.domain.repository.PermissionRepository;
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
public class PermissionService {
    private final PermissionRepository repository;
    private final PermissionMapper permissionMapper;

    public PermissionService(PermissionRepository repository, PermissionMapper permissionMapper) {
        this.repository = repository;
        this.permissionMapper = permissionMapper;
    }

    public PermissionDto save(PermissionDto permissionDto) {
        Permission entity = permissionMapper.toEntity(permissionDto);
        return permissionMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public PermissionDto findById(Long id) {
        return permissionMapper.toDto(repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    public Page<PermissionDto> findByCondition(PermissionDto permissionDto, Pageable pageable) {
        Page<Permission> entityPage = repository.findAll(pageable);
        List<Permission> entities = entityPage.getContent();
        return new PageImpl<>(permissionMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public PermissionDto update(PermissionDto permissionDto, Long id) {
        PermissionDto data = findById(id);
        Permission entity = permissionMapper.toEntity(permissionDto);
        BeanUtils.copyProperties(data, entity);
        return save(permissionMapper.toDto(entity));
    }
}
