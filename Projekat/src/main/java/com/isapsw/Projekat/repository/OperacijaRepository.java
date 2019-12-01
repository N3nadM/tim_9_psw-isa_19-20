package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Operacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperacijaRepository extends JpaRepository<Operacija, Long> {
    List<Operacija> findOperacijeByPacijentId(Long id);
}
