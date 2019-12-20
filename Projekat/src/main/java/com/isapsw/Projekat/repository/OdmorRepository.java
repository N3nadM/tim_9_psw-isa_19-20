package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Odmor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdmorRepository extends JpaRepository<Odmor, Long> {
}
