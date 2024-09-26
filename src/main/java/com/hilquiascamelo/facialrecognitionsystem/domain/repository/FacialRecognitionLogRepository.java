package com.hilquiascamelo.facialrecognitionsystem.domain.repository;

import com.hilquiascamelo.facialrecognitionsystem.domain.model.FacialRecognitionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacialRecognitionLogRepository extends JpaRepository<FacialRecognitionLog, Long> {
}