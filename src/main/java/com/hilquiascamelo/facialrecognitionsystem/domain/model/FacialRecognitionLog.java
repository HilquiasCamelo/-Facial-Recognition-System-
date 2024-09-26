package com.hilquiascamelo.facialrecognitionsystem.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "facial_recognition_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacialRecognitionLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person; // Pode ser NULL se a pessoa não for reconhecida

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @Column(name = "recognized", nullable = false)
    private boolean recognized;

    @Column(name = "recognition_timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime recognitionTimestamp = LocalDateTime.now();
}

