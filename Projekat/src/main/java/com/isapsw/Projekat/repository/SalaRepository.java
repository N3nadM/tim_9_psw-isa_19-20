package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Pregled;
import com.isapsw.Projekat.domain.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

    Optional<Sala> findById(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    @Query("SELECT s FROM Sala s WHERE s.id = :id")
    Optional<Sala> findByIdTransaction(@Param("id") Long id);

    @Query("SELECT DISTINCT s FROM Sala s WHERE s.klinika.id = :id AND s.aktivna = true")
    List<Sala> findByKlinikaId(@Param("id") Long id);

    @Query("SELECT DISTINCT s FROM Sala s WHERE UPPER(s.naziv) LIKE %:naziv% AND UPPER(s.salaIdentifier) LIKE %:salaIdentifier% AND s.aktivna = true")
    List<Sala> findSalaByParameters(@Param("salaIdentifier") String salaIdentifier, @Param("naziv") String naziv);

    @Query("SELECT s.pregled FROM Sala s WHERE s.id = :id")
    List<Pregled> getPregledi(@Param("id") Long id);

}
