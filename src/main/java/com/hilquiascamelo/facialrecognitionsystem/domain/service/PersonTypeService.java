package com.hilquiascamelo.facialrecognitionsystem.domain.service;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.PersonTypeDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.mapper.PersonTypeMapper;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.PersonType;
import com.hilquiascamelo.facialrecognitionsystem.domain.repository.PersonTypeRepository;
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
public class PersonTypeService {
    private final PersonTypeRepository repository;
    private final PersonTypeMapper personTypeMapper;

    public PersonTypeService(PersonTypeRepository repository, PersonTypeMapper personTypeMapper) {
        this.repository = repository;
        this.personTypeMapper = personTypeMapper;
    }

    public PersonTypeDto save(PersonTypeDto personTypeDto) {
        PersonType entity = personTypeMapper.toEntity(personTypeDto);
        return personTypeMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public PersonTypeDto findById(Long id) {
        return personTypeMapper.toDto(repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    public Page<PersonTypeDto> findByCondition(PersonTypeDto personTypeDto, Pageable pageable) {
        Page<PersonType> entityPage = repository.findAll(pageable);
        List<PersonType> entities = entityPage.getContent();
        return new PageImpl<>(personTypeMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public PersonTypeDto update(PersonTypeDto personTypeDto, Long id) {
        PersonTypeDto data = findById(id);
        PersonType entity = personTypeMapper.toEntity(personTypeDto);
        BeanUtils.copyProperties(data, entity);
        return save(personTypeMapper.toDto(entity));
    }
}
