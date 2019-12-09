package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.OcenaKlinike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OcenaKlinikeRepository extends JpaRepository<OcenaKlinike, Long> {

    OcenaKlinike findByOcKlinikeIdentifier(String id);

    @Query("SELECT AVG(o.ocena) FROM OcenaKlinike o WHERE o.klinika.id = :id")
    Double calculateAverage(@Param("id") Long id);
}
