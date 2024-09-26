package com.hilquiascamelo.facialrecognitionsystem.domain.repository;

import com.hilquiascamelo.facialrecognitionsystem.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}