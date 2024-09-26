package com.hilquiascamelo.facialrecognitionsystem.domain.dto;

import com.hilquiascamelo.facialrecognitionsystem.domain.model.Person;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Schema
public class PermissionDto extends AbstractDto<Long> {
    private Long id;
    private Person person;
    @NotNull
    private boolean exitPermission;
}