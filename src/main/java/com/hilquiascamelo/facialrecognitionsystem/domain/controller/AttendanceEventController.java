package com.hilquiascamelo.facialrecognitionsystem.domain.controller;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.AttendanceEventDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.mapper.AttendanceEventMapper;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.AttendanceEvent;
import com.hilquiascamelo.facialrecognitionsystem.domain.service.AttendanceEventService;
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

@RequestMapping("/attendance-event")
@RestController
@Slf4j
@Schema(description = "attendance-event")
public class AttendanceEventController {
    private final AttendanceEventService attendanceEventService;

    private static final Logger log = LoggerFactory.getLogger(AttendanceEventController.class);

    public AttendanceEventController(AttendanceEventService attendanceEventService) {
        this.attendanceEventService = attendanceEventService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated AttendanceEventDto attendanceEventDto) {
        attendanceEventService.save(attendanceEventDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceEventDto> findById(@PathVariable("id") Long id) {
        AttendanceEventDto attendanceEvent = attendanceEventService.findById(id);
        return ResponseEntity.ok(attendanceEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(attendanceEventService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data!");
            return new ResourceNotFoundException();
        });
        attendanceEventService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<AttendanceEventDto>> pageQuery(AttendanceEventDto attendanceEventDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<AttendanceEventDto> attendanceEventPage = attendanceEventService.findByCondition(attendanceEventDto, pageable);
        return ResponseEntity.ok(attendanceEventPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated AttendanceEventDto attendanceEventDto, @PathVariable("id") Long id) {
        attendanceEventService.update(attendanceEventDto, id);
        return ResponseEntity.ok().build();
    }
}
