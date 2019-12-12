package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.TipPregleda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipPregledaRepository extends JpaRepository<TipPregleda, Long> {
    List<TipPregleda> findAll();
    Optional<TipPregleda> findById(Long id);
    List<TipPregleda> findTipPregledasByKlinikaId(Long id);


}
