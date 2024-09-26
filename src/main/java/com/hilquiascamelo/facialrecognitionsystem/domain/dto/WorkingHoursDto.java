package com.hilquiascamelo.facialrecognitionsystem.domain.dto;

import com.hilquiascamelo.facialrecognitionsystem.domain.model.Location;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.PersonType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Schema
public class WorkingHoursDto extends AbstractDto<Long> {
    private Long id;
    private Location location;
    private PersonType personType;
    private LocalTime allowedEntryTime;
    private LocalTime allowedExitTime;
}