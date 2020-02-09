package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.TipPregleda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoviPregledaRepository extends JpaRepository<TipPregleda, Long> {

    @Query("SELECT DISTINCT t.naziv FROM TipPregleda t WHERE t.naziv IN (SELECT DISTINCT tp.naziv FROM TipPregleda tp) AND t.aktivan = true" )
    List<String> findAllTpString();


    TipPregleda findTipPregledaByMinimalnoTrajanjeMin(int trajanje);

    @Query("SELECT tp.minimalnoTrajanjeMin FROM TipPregleda tp WHERE tp.id = :id")
    Integer getMinimalnoTrajanje(@Param("id") Long id);


}
