package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.OcenaKlinike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

@Repository
public interface OcenaKlinikeRepository extends JpaRepository<OcenaKlinike, Long> {

    OcenaKlinike findByOcKlinikeIdentifier(String id);

    @Query("SELECT AVG(o.ocena) FROM OcenaKlinike o WHERE o.klinika.id = :id")
    Double calculateAverage(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    @Query("SELECT ok FROM OcenaKlinike ok WHERE ok.ocKlinikeIdentifier = :id")
    OcenaKlinike findByOcKlinikeIdentifierTransaction(@Param("id") String id);
}
