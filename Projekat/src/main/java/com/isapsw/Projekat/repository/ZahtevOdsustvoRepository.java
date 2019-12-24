package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.ZahtevOdsustvo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZahtevOdsustvoRepository extends JpaRepository<ZahtevOdsustvo, Long> {

    List<ZahtevOdsustvo> findAll();

    List<ZahtevOdsustvo> findAllByKlinikaId(Long id);

    Optional<ZahtevOdsustvo> findById(Long id);
}
