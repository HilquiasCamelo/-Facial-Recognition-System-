package com.hilquiascamelo.facialrecognitionsystem.domain.dto;

import com.hilquiascamelo.facialrecognitionsystem.domain.model.PersonType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Schema
public class PersonDto extends AbstractDto<Long> {
    private Long id;
    @Size(max = 100)
    @NotBlank
    private String name;
    @Size(max = 20)
    @NotBlank
    private String idDocument;
    private PersonType personType;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime registrationDate;
}