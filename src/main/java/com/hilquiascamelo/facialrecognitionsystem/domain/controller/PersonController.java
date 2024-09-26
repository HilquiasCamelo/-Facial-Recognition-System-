package com.hilquiascamelo.facialrecognitionsystem.domain.controller;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.PersonDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.mapper.PersonMapper;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.Person;
import com.hilquiascamelo.facialrecognitionsystem.domain.service.PersonService;
import com.hilquiascamelo.facialrecognitionsystem.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/person")
@RestController
@Slf4j
@Schema(description = "person")
public class PersonController {
    private final PersonService personService;
    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated PersonDto personDto) {
        personService.save(personDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> findById(@PathVariable("id") Long id) {
        PersonDto person = personService.findById(id);
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(personService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data!");
            return new ResourceNotFoundException();
        });
        personService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<PersonDto>> pageQuery(PersonDto personDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PersonDto> personPage = personService.findByCondition(personDto, pageable);
        return ResponseEntity.ok(personPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated PersonDto personDto, @PathVariable("id") Long id) {
        personService.update(personDto, id);
        return ResponseEntity.ok().build();
    }
}
