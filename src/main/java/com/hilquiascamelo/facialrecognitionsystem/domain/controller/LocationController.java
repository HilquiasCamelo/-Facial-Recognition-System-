package com.hilquiascamelo.facialrecognitionsystem.domain.controller;

import com.hilquiascamelo.facialrecognitionsystem.domain.dto.LocationDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.mapper.LocationMapper;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.Location;
import com.hilquiascamelo.facialrecognitionsystem.domain.service.LocationService;
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

@RequestMapping("/location")
@RestController
@Slf4j
@Schema(  description = "location")
public class LocationController {
    private final LocationService locationService;

    private static final Logger log = LoggerFactory.getLogger(LocationController.class);

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated LocationDto locationDto) {
        locationService.save(locationDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> findById(@PathVariable("id") Long id) {
        LocationDto location = locationService.findById(id);
        return ResponseEntity.ok(location);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(locationService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data!");
            return new ResourceNotFoundException();
        });
        locationService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<LocationDto>> pageQuery(LocationDto locationDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<LocationDto> locationPage = locationService.findByCondition(locationDto, pageable);
        return ResponseEntity.ok(locationPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated LocationDto locationDto, @PathVariable("id") Long id) {
        locationService.update(locationDto, id);
        return ResponseEntity.ok().build();
    }
}
