package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Zahtev;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZahtevRepository extends JpaRepository<Zahtev, Long> {

    List<Zahtev> findAll();

    void deleteById(Long id);

    Zahtev getZahtevByJbzo(String jbzo);

    Zahtev findZahtevByEmail(String email);

    void deleteByEmail(String email);

    Zahtev getZahtevByEmail(String email);
}
