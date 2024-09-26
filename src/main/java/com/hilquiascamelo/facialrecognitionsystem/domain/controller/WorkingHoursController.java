package com.hilquiascamelo.facialrecognitionsystem.domain.controller;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.WorkingHoursDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.mapper.WorkingHoursMapper;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.WorkingHours;
import com.hilquiascamelo.facialrecognitionsystem.domain.service.WorkingHoursService;
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

@RequestMapping("/working-hours")
@RestController
@Slf4j
@Schema(description = "working-hours")
public class WorkingHoursController {
    private final WorkingHoursService workingHoursService;

    private static final Logger log = LoggerFactory.getLogger(WorkingHoursService.class);

    public WorkingHoursController(WorkingHoursService workingHoursService) {
        this.workingHoursService = workingHoursService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated WorkingHoursDto workingHoursDto) {
        workingHoursService.save(workingHoursDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkingHoursDto> findById(@PathVariable("id") Long id) {
        WorkingHoursDto workingHours = workingHoursService.findById(id);
        return ResponseEntity.ok(workingHours);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(workingHoursService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data!");
            return new ResourceNotFoundException();
        });
        workingHoursService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<WorkingHoursDto>> pageQuery(WorkingHoursDto workingHoursDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<WorkingHoursDto> workingHoursPage = workingHoursService.findByCondition(workingHoursDto, pageable);
        return ResponseEntity.ok(workingHoursPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated WorkingHoursDto workingHoursDto, @PathVariable("id") Long id) {
        workingHoursService.update(workingHoursDto, id);
        return ResponseEntity.ok().build();
    }
}
