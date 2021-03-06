package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.KorisnikDTO;
import com.isapsw.Projekat.dto.LekarDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

@Repository
public interface LekarRepository extends JpaRepository<Lekar, Long> {

    Lekar findLekarByKorisnikId(Long id);

    Lekar findLekarById(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    @Query("SELECT l FROM Lekar l WHERE l.id = :id")
    Optional<Lekar> findByIdTransaction(@Param("id") Long id);

    @Query("SELECT DISTINCT l FROM Lekar l WHERE l.klinika.id = :id AND l.aktivan = true")
    List<Lekar> findLekarsByKlinikaId(@Param("id") Long id);

    @Query("SELECT DISTINCT k.id FROM Lekar l JOIN Korisnik k ON l.korisnik.id = k.id WHERE UPPER(k.ime) LIKE %:ime% AND UPPER(k.prezime) LIKE %:prezime% AND UPPER(k.email) LIKE %:email% AND l.aktivan = true")
    List<Long> findLekarByParameters(@Param("ime") String ime, @Param("prezime") String prezime, @Param("email") String email);

    @Query("SELECT l.korisnik.id FROM Lekar l WHERE l.id = :idLekar")
    Long getKorisnikId(@Param("idLekar") Long idLekar);

    @Query("SELECT l.pregledi FROM Lekar l WHERE l.id = :id")
    List<Pregled> getPregledi(@Param("id") Long id);

    @Query("SELECT l.klinika FROM Lekar l WHERE l.id = :id")
    Klinika getKlinika(@Param("id") Long id);

    @Query("SELECT  DISTINCT l.tipPregleda  FROM Lekar l WHERE l.klinika.id = :idKlinika")
    List<TipPregleda> getTipoviSvihLekara(@Param("idKlinika") Long idKlinika);

}
