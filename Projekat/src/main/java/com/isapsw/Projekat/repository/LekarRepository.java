package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Lekar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LekarRepository extends JpaRepository<Lekar, Long> {

    Lekar findLekarByKorisnikId(Long id);
}
