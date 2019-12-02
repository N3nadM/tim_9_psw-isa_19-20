package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Klinika;
import com.isapsw.Projekat.domain.Korisnik;
import com.isapsw.Projekat.domain.Pacijent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacijentRepository extends JpaRepository<Pacijent, Long> {

    Pacijent getPacijentByJbzo(String jbzo);

    Pacijent findPacijentByKorisnikId(Long id);

    @Query("SELECT DISTINCT k.id FROM Pacijent p JOIN Korisnik k ON p.korisnik.id = k.id WHERE k.ime LIKE %:ime% AND UPPER(k.prezime) LIKE %:prezime% AND UPPER(k.email) LIKE %:email% AND UPPER(k.grad) LIKE %:grad% AND p.jbzo LIKE %:jbzo% ")
    List<Long> findPacijentByParameters(@Param("ime") String ime, @Param("prezime") String prezime, @Param("email") String email, @Param("grad") String grad, @Param("jbzo") String jbzo);
}
