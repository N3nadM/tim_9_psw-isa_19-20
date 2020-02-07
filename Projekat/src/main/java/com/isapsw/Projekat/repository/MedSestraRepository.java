package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.MedicinskaSestra;
import com.isapsw.Projekat.domain.Pregled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedSestraRepository extends JpaRepository<MedicinskaSestra, Long> {


    Optional<MedicinskaSestra> findById(Long aLong);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    @Query("SELECT s FROM MedicinskaSestra s WHERE s.id = :id")
    Optional<MedicinskaSestra> findByIdTransaction(@Param("id") Long id);

    MedicinskaSestra findMedicinskaSestraByKorisnikId(Long id);

    List<MedicinskaSestra> findMedicinskaSestrasByKlinikaId(Long id);

    @Query("SELECT ms.pregledi FROM MedicinskaSestra ms WHERE ms.id = :id")
    List<Pregled> getPregledi(@Param("id") Long id);
}
