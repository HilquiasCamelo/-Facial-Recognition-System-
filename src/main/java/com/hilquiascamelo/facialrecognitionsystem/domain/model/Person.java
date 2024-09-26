package com.hilquiascamelo.facialrecognitionsystem.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "people")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "id_document", nullable = false, unique = true, length = 20)
    private String idDocument;

    @ManyToOne
    @JoinColumn(name = "person_type_id", nullable = false)
    private PersonType personType;

    @Column(name = "registration_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime registrationDate = LocalDateTime.now();
}
