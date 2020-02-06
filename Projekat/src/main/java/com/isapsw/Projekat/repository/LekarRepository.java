package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Authority;
import com.isapsw.Projekat.domain.Korisnik;
import com.isapsw.Projekat.domain.Lekar;
import com.isapsw.Projekat.dto.KorisnikDTO;
import com.isapsw.Projekat.dto.LekarDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LekarRepository extends JpaRepository<Lekar, Long> {

    Lekar findLekarByKorisnikId(Long id);

    Lekar findLekarById(Long id);

    @Query("SELECT DISTINCT l FROM Lekar l WHERE l.klinika.id = :id AND l.aktivan = true")
    List<Lekar> findLekarsByKlinikaId(@Param("id") Long id);

    @Query("SELECT DISTINCT k.id FROM Lekar l JOIN Korisnik k ON l.korisnik.id = k.id WHERE UPPER(k.ime) LIKE %:ime% AND UPPER(k.prezime) LIKE %:prezime% AND UPPER(k.email) LIKE %:email% AND l.aktivan = true")
    List<Long> findLekarByParameters(@Param("ime") String ime, @Param("prezime") String prezime, @Param("email") String email);

}
