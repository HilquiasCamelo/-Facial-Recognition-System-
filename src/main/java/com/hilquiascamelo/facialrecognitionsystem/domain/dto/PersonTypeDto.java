package com.hilquiascamelo.facialrecognitionsystem.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Schema
public class PersonTypeDto extends AbstractDto<Long> {
    private Long id;
    @Size(max = 255)
    @NotBlank
    private String typeName;
}