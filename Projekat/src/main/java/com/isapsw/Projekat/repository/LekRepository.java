package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Lek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LekRepository extends JpaRepository<Lek, Long> {

    Lek findLekById(Long id);

    Lek findLekByNaziv(String naziv);

    List<Lek> findAll();
}
