package com.hilquiascamelo.facialrecognitionsystem.domain.controller;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.FacialRecognitionLogDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.mapper.FacialRecognitionLogMapper;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.FacialRecognitionLog;
import com.hilquiascamelo.facialrecognitionsystem.domain.service.FacialRecognitionLogService;
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

import javax.management.Descriptor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/facial-recognition-log")
@RestController
@Slf4j
@Schema(description = "facial-recognition-log")
public class FacialRecognitionLogController {

    private static final Logger log = LoggerFactory.getLogger(FacialRecognitionLogController.class);

    private final FacialRecognitionLogService facialRecognitionLogService;

    public FacialRecognitionLogController(FacialRecognitionLogService facialRecognitionLogService) {
        this.facialRecognitionLogService = facialRecognitionLogService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated FacialRecognitionLogDto facialRecognitionLogDto) {
        facialRecognitionLogService.save(facialRecognitionLogDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacialRecognitionLogDto> findById(@PathVariable("id") Long id) {
        FacialRecognitionLogDto facialRecognitionLog = facialRecognitionLogService.findById(id);
        return ResponseEntity.ok(facialRecognitionLog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(facialRecognitionLogService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data!");
            return new ResourceNotFoundException();
        });
        facialRecognitionLogService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<FacialRecognitionLogDto>> pageQuery(FacialRecognitionLogDto facialRecognitionLogDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<FacialRecognitionLogDto> facialRecognitionLogPage = facialRecognitionLogService.findByCondition(facialRecognitionLogDto, pageable);
        return ResponseEntity.ok(facialRecognitionLogPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated FacialRecognitionLogDto facialRecognitionLogDto, @PathVariable("id") Long id) {
        facialRecognitionLogService.update(facialRecognitionLogDto, id);
        return ResponseEntity.ok().build();
    }
}
