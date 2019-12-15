package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Pregled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PregledRepository extends JpaRepository<Pregled, Long> {

    @Query("SELECT p FROM Pregled p WHERE CAST(p.datumPocetka AS date) = CAST(:datum AS date)")
    List<Pregled> findByPregledDatumPocetka(Date datum);

    List<Pregled> findPregledByPacijentId(Long id);

    List<Pregled> findPregledByLekarId(Long id);

    List<Pregled> findPregledsByMedicinskaSestraId(Long id);

    List<Pregled> findPregledBySalaId(Long id);

    @Query("SELECT p FROM Pregled p WHERE p.sala.klinika.id = :klinikaId AND p.pacijent.id is null")
    List<Pregled> findPregledBySalaKlinikaId(@Param("klinikaId") Long klinikaId);

    @Query("SELECT p FROM Pregled p WHERE p.lekar.id = :lekarId AND p.pacijent.id = :pacijentId AND p.datumZavrsetka < :datum")
    List<Pregled> findPregledByPacijentIdAndLekarId(@Param("lekarId") Long lekarId, @Param("pacijentId")Long pacijentId, @Param("datum") Date datum);
}
