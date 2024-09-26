package com.hilquiascamelo.facialrecognitionsystem.domain.dto;

import com.hilquiascamelo.facialrecognitionsystem.domain.model.Person;
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
public class FacialRecognitionLogDto extends AbstractDto<Long> {
    private Long id;
    private Person person;
    @Size(max = 255)
    @NotBlank
    private String imagePath;
    @NotNull
    private boolean recognized;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime recognitionTimestamp;
}