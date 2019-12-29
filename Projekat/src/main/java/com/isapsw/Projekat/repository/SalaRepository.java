package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

    Optional<Sala> findById(Long id);

    @Query("SELECT DISTINCT s FROM Sala s WHERE s.klinika.id = :id AND s.aktivna = true")
    List<Sala> findByKlinikaId(@Param("id") Long id);

    @Query("SELECT DISTINCT s FROM Sala s WHERE UPPER(s.naziv) LIKE %:naziv% AND UPPER(s.salaIdentifier) LIKE %:salaIdentifier% AND s.aktivna = true")
    List<Sala> findSalaByParameters(@Param("salaIdentifier") String salaIdentifier, @Param("naziv") String naziv);

}
