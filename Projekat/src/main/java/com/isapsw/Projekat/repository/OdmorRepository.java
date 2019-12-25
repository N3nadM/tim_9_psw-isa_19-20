package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Odmor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OdmorRepository extends JpaRepository<Odmor, Long> {

    @Query("SELECT o FROM Odmor o WHERE o.lekar.id = :id AND o.datumOd < :datum AND o.datumDo > :datum")
    Odmor proveraZaLekara(@Param("id") Long id, @Param("datum") Date datum);

    @Query("SELECT o FROM Odmor o WHERE o.medicinskaSestra.id = :id AND o.datumOd < :datum AND o.datumDo > :datum")
    Odmor proveraZaSestru(@Param("id") Long id, @Param("datum") Date datum);
}
