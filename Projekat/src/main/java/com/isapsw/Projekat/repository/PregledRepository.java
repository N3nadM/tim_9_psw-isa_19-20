package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Pregled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PregledRepository extends JpaRepository<Pregled, Long> {

    List<Pregled> findPregledByPacijentId(Long id);
}
