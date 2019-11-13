package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Pacijent_Zahtev;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pacijent_ZahtevRepository extends JpaRepository<Pacijent_Zahtev, Long> {

    List<Pacijent_Zahtev> findAll();
}
