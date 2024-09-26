package com.hilquiascamelo.facialrecognitionsystem.domain.dto;

import java.time.LocalDateTime;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class AbstractDto<E> {

    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

    // @CreatedBy
    // Depending on your business decision
    private String createdBy;

    // @LastModifiedBy
    // Depending on your business decision
    private String lastModifiedBy;
}
