package com.hilquiascamelo.facialrecognitionsystem.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "working_hours")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkingHours implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "person_type_id", nullable = false)
    private PersonType personType;

    @Column(name = "allowed_entry_time")
    private LocalTime allowedEntryTime;

    @Column(name = "allowed_exit_time")
    private LocalTime allowedExitTime;
}

