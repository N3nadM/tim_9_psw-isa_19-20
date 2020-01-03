package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Recept;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceptRepository extends JpaRepository<Recept, Long> {

    Optional<Recept> findById(Long id);

    List<Recept> findAllByMedicinskaSestraId(Long id);

}
