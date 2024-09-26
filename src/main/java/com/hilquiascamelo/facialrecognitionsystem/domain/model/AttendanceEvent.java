package com.hilquiascamelo.facialrecognitionsystem.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance_events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "event_timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime eventTimestamp = LocalDateTime.now();

    @Column(name = "event_type", nullable = false, length = 10)
    private String eventType; // 'Entrada' ou 'Saída'

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
}

