package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Operacija;
import com.isapsw.Projekat.domain.Pregled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OperacijaRepository extends JpaRepository<Operacija, Long> {
    List<Operacija> findOperacijeByPacijentId(Long id);

    List<Operacija> findOperacijasByMedicinskaSestraId(Long id);

    List<Operacija> findOperacijasBySalaId(Long id);

    @Query("SELECT o FROM Operacija o LEFT JOIN o.lekari l WHERE l.id = :id")
    List<Operacija> findOperacijasByLekari(@Param("id") Long id);

    @Query("SELECT o FROM Operacija o LEFT JOIN o.lekari l WHERE l.id = :lekarId AND o.pacijent.id = :pacijentId AND o.datumZavrsetka < :datum")
    List<Operacija> findOperacijeByPacijentIdAndLekarId(@Param("lekarId") Long lekarId, @Param("pacijentId")Long pacijentId, @Param("datum") Date datum);

    @Query("SELECT o FROM Operacija o WHERE o.medicinskaSestra.id = :id AND o.datumPocetka BETWEEN :datum AND :tomorrow")
    List<Operacija> findSestraOperacijeDatum(@Param("id") Long id, @Param("datum") Date datum, @Param("tomorrow") Date tomorrow);

    @Query("SELECT o FROM Operacija o WHERE o.datumPocetka BETWEEN :datum AND :tomorrow")
    List<Operacija> findOperacijeDatum(@Param("datum") Date datum, @Param("tomorrow") Date tomorrow);
}
