package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.ZahtevOdmor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZahtevOdmorRepository extends JpaRepository<ZahtevOdmor, Long> {

    List<ZahtevOdmor> findAll();

    List<ZahtevOdmor> findAllByKlinikaId(Long id);

    Optional<ZahtevOdmor> findById(Long id);
}
