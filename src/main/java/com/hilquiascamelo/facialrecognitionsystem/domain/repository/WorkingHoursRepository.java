package com.hilquiascamelo.facialrecognitionsystem.domain.repository;

import com.hilquiascamelo.facialrecognitionsystem.domain.model.WorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingHoursRepository extends JpaRepository<WorkingHours, Long> {
}