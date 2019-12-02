package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Pregled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PregledRepository extends JpaRepository<Pregled, Long> {

    @Query("SELECT p FROM Pregled p WHERE CAST(p.datumPocetka AS date) = CAST(:datum AS date)")
    List<Pregled> findByPregledDatumPocetka(Date datum);

    List<Pregled> findPregledByPacijentId(Long id);
}