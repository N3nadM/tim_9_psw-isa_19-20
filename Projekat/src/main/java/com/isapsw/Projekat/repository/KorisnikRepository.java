package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    Korisnik getKorisnikByEmail(String email);

    Korisnik findKorisnikById(Long id);

    Korisnik findByEmail(String s);
}
