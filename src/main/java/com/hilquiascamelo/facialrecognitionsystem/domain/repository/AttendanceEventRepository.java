package com.hilquiascamelo.facialrecognitionsystem.domain.repository;

import com.hilquiascamelo.facialrecognitionsystem.domain.model.AttendanceEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceEventRepository extends JpaRepository<AttendanceEvent, Long> {
}