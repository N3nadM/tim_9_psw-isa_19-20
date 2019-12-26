package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.TipPregleda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TipPregledaRepository extends JpaRepository<TipPregleda, Long> {

    @Query("SELECT DISTINCT tp FROM TipPregleda tp WHERE tp.aktivan = true")
    List<TipPregleda> findAll();

    Optional<TipPregleda> findById(Long id);

    @Query("SELECT DISTINCT tp FROM TipPregleda tp WHERE tp.aktivan = true AND tp.klinika.id = :id")
    List<TipPregleda> findTipPregledasByKlinikaId(@Param("id") Long id);

    @Query("SELECT DISTINCT tp FROM TipPregleda tp JOIN Klinika k ON tp.klinika.id = k.id  WHERE k.id = :id AND UPPER(tp.naziv) LIKE %:naziv% AND tp.cenaPregleda < :najvecaCena AND tp.minimalnoTrajanjeMin > :minimalnoTrajanjeMin AND tp.aktivan = true")
    List<TipPregleda> findTipByParameters(@Param("id") Long id, @Param("naziv") String naziv, @Param("najvecaCena") Integer najvecaCena, @Param("minimalnoTrajanjeMin")Integer minimalnoTrajanjeMin );

}
