package com.hilquiascamelo.facialrecognitionsystem.domain.controller;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.PermissionDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.mapper.PermissionMapper;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.Permission;
import com.hilquiascamelo.facialrecognitionsystem.domain.service.PermissionService;
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

@RequestMapping("/permission")
@RestController
@Slf4j
@Schema(description = "permission")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
    private static final Logger log = LoggerFactory.getLogger(PermissionService.class);

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated PermissionDto permissionDto) {
        permissionService.save(permissionDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDto> findById(@PathVariable("id") Long id) {
        PermissionDto permission = permissionService.findById(id);
        return ResponseEntity.ok(permission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(permissionService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data!");
            return new ResourceNotFoundException();
        });
        permissionService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<PermissionDto>> pageQuery(PermissionDto permissionDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PermissionDto> permissionPage = permissionService.findByCondition(permissionDto, pageable);
        return ResponseEntity.ok(permissionPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated PermissionDto permissionDto, @PathVariable("id") Long id) {
        permissionService.update(permissionDto, id);
        return ResponseEntity.ok().build();
    }
}
