package com.hilquiascamelo.facialrecognitionsystem.domain.repository;

import com.hilquiascamelo.facialrecognitionsystem.domain.model.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonTypeRepository extends JpaRepository<PersonType, Long> {
}