package com.hilquiascamelo.facialrecognitionsystem.domain.service;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.PersonDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.mapper.PersonMapper;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.Person;
import com.hilquiascamelo.facialrecognitionsystem.domain.repository.PersonRepository;
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
public class PersonService {
    private final PersonRepository repository;
    private final PersonMapper personMapper;

    public PersonService(PersonRepository repository, PersonMapper personMapper) {
        this.repository = repository;
        this.personMapper = personMapper;
    }

    public PersonDto save(PersonDto personDto) {
        Person entity = personMapper.toEntity(personDto);
        return personMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public PersonDto findById(Long id) {
        return personMapper.toDto(repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    public Page<PersonDto> findByCondition(PersonDto personDto, Pageable pageable) {
        Page<Person> entityPage = repository.findAll(pageable);
        List<Person> entities = entityPage.getContent();
        return new PageImpl<>(personMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public PersonDto update(PersonDto personDto, Long id) {
        PersonDto data = findById(id);
        Person entity = personMapper.toEntity(personDto);
        BeanUtils.copyProperties(data, entity);
        return save(personMapper.toDto(entity));
    }
}
