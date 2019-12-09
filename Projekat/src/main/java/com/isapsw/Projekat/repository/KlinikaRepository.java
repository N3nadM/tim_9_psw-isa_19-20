package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Klinika;
import com.isapsw.Projekat.domain.Pacijent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface KlinikaRepository extends JpaRepository<Klinika, Long> {
    Klinika findKlinikaById(Long id);
    List<Klinika> findAll();

    @Query("SELECT DISTINCT k FROM Klinika k JOIN k.lekari l WHERE l.tipPregleda.naziv LIKE %:tip% AND UPPER(k.adresa) LIKE %:lokacija% AND k.ocena >= :ocena")
    List<Klinika> findKlinikaByParameters(@Param("lokacija") String lokacija, @Param("tip") String tip, @Param("ocena") Double ocena);

//
//    @Query("SELECT DISTINCT k FROM Klinika k JOIN k.lekari l WHERE l.tipPregleda.naziv LIKE %:tip% AND UPPER(k.adresa) LIKE %:lokacija% AND k.ocena >= :ocena")
//    List<Klinika> findKlinikaByParametersAndDate(@Param("lokacija") String lokacija, @Param("tip") String tip, @Param("ocena") Double ocena, @Param("datum") Date datum);

    @Query("Select k from Klinika k where UPPER(k.adresa) LIKE %:lokacija%")
    List<Klinika> findKlinikaByAdresaContaining(@Param("lokacija") String lokacija);

    @Query("Select k from Klinika k where UPPER(k.adresa) LIKE %:lokacija% AND :ocena <= (SELECT AVG(oc.ocena) FROM OcenaKlinike oc WHERE k.id = oc.klinika)")
    List<Klinika> findKlinikaByAdresaAndOcena(@Param("lokacija") String lokacija, @Param("ocena") Double ocena);

    Optional<Klinika> findById(Long id);

    @Query("SELECT p FROM Klinika k LEFT JOIN k.pacijenti p WHERE k.id = :klinikaId AND p.id = :pacijentId")
    Pacijent findPacijentInKlinika(@Param("klinikaId") Long klinikaId, @Param("pacijentId") Long pacijentId);

}
