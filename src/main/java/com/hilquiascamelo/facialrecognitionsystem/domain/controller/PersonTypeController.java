package com.hilquiascamelo.facialrecognitionsystem.domain.controller;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.PersonTypeDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.mapper.PersonTypeMapper;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.PersonType;
import com.hilquiascamelo.facialrecognitionsystem.domain.service.PersonTypeService;
import com.hilquiascamelo.facialrecognitionsystem.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.log4j.Log4j;
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

@RequestMapping("/api/person-type")
@RestController
@Log4j
@Schema(description = "person-type")
public class PersonTypeController {
    private final PersonTypeService personTypeService;

    private static final Logger log = LoggerFactory.getLogger(PersonTypeService.class);

    public PersonTypeController(PersonTypeService personTypeService) {
        this.personTypeService = personTypeService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated PersonTypeDto personTypeDto) {
        personTypeService.save(personTypeDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonTypeDto> findById(@PathVariable("id") Long id) {
        PersonTypeDto personType = personTypeService.findById(id);
        return ResponseEntity.ok(personType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(personTypeService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data!");
            return new ResourceNotFoundException();
        });
        personTypeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<PersonTypeDto>> pageQuery(PersonTypeDto personTypeDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PersonTypeDto> personTypePage = personTypeService.findByCondition(personTypeDto, pageable);
        return ResponseEntity.ok(personTypePage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated PersonTypeDto personTypeDto, @PathVariable("id") Long id) {
        personTypeService.update(personTypeDto, id);
        return ResponseEntity.ok().build();
    }
}
