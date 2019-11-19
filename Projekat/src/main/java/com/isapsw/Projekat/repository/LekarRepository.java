package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Lekar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LekarRepository extends JpaRepository<Lekar, Long> {

    Lekar findLekarByKorisnikId(Long id);
}
