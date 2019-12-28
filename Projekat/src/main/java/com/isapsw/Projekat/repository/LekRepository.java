package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Lek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LekRepository extends JpaRepository<Lek, Long> {

    Lek findLekById(Long id);

    @Query("SELECT d.terapija FROM Dijagnoza d WHERE d.id = :id")
   List<Lek> findByDijagnozasId(@Param("id") Long id);

    Lek findLekByNaziv(String naziv);

    List<Lek> findAll();
}
