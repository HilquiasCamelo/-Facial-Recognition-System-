package com.hilquiascamelo.facialrecognitionsystem.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "person_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name", nullable = false, unique = true)
    private String typeName;
}
