package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.TipPregleda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipPregledaRepository extends JpaRepository<TipPregleda, Long> {
    List<TipPregleda> findAll();
    Optional<TipPregleda> findById(Long id);
    List<TipPregleda> findTipPregledasByKlinikaId(Long id);

    @Query("SELECT DISTINCT tp FROM TipPregleda tp JOIN Klinika k ON tp.klinika.id = k.id  WHERE k.id = :id AND UPPER(tp.naziv) LIKE %:naziv% AND tp.cenaPregleda < :najvecaCena AND tp.minimalnoTrajanjeMin > :minimalnoTrajanjeMin")
    List<TipPregleda> findTipByParameters(@Param("id") Long id, @Param("naziv") String naziv, @Param("najvecaCena") Integer najvecaCena, @Param("minimalnoTrajanjeMin")Integer minimalnoTrajanjeMin );

}
