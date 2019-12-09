package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Operacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OperacijaRepository extends JpaRepository<Operacija, Long> {
    List<Operacija> findOperacijeByPacijentId(Long id);

    @Query("SELECT o FROM Operacija o LEFT JOIN o.lekari l WHERE l.id = :lekarId AND o.pacijent.id = :pacijentId AND o.datumZavrsetka < :datum")
    List<Operacija> findOperacijeByPacijentIdAndLekarId(@Param("lekarId") Long lekarId, @Param("pacijentId")Long pacijentId, @Param("datum") Date datum);
}
