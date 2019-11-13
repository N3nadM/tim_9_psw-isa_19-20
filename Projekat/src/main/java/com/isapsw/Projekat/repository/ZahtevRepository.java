package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Zahtev;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZahtevRepository extends JpaRepository<Zahtev, Long> {

    List<Zahtev> findAll();

    void deleteById(Long id);
}
