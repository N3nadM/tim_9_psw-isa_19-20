package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Authority;
import com.isapsw.Projekat.domain.Korisnik;
import com.isapsw.Projekat.domain.Lekar;
import com.isapsw.Projekat.dto.KorisnikDTO;
import com.isapsw.Projekat.dto.LekarDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LekarRepository extends JpaRepository<Lekar, Long> {

    Lekar findLekarByKorisnikId(Long id);

    Lekar findLekarById(Long id);
}
