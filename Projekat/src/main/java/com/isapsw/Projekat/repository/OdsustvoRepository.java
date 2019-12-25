package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Odsustvo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OdsustvoRepository extends JpaRepository<Odsustvo, Long> {

    @Query("SELECT o FROM Odsustvo o WHERE o.lekar.id = :id AND o.datum = :datum")
    Odsustvo proveraZaLekara(@Param("id") Long id, @Param("datum") Date datum);

    @Query("SELECT o FROM Odsustvo o WHERE o.medicinskaSestra.id = :id AND o.datum = :datum")
    Odsustvo proveraZaSestru(@Param("id") Long id, @Param("datum") Date datum);
}
