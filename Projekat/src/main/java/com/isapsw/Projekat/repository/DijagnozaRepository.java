package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Dijagnoza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DijagnozaRepository extends JpaRepository<Dijagnoza, Long> {

    Dijagnoza findDijagnozaById(Long id);

    List<Dijagnoza> findAll();
}
