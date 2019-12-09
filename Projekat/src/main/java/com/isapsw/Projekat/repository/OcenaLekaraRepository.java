package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.OcenaLekara;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcenaLekaraRepository extends JpaRepository<OcenaLekara, Long> {

    OcenaLekara findOcenaLekaraByOcLekaraIdentifier(String identifier);

    @Query("SELECT o FROM OcenaLekara o WHERE o.ocLekaraIdentifier IN (:idjevi)")
    List<OcenaLekara> findLekariByIds(@Param("idjevi") List<String> idjevi);

    @Query("SELECT AVG(o.ocena) FROM OcenaLekara o WHERE o.lekar.id = :id")
    Double calculateAverage(@Param("id") Long id);
}
