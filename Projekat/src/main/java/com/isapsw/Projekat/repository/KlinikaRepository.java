package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Klinika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KlinikaRepository extends JpaRepository<Klinika, Long> {
    Klinika findKlinikaById(Long id);
    List<Klinika> findAll();

    @Query("Select k from Klinika k where UPPER(k.adresa) LIKE %:lokacija%")
    List<Klinika> findKlinikaByAdresaContaining(@Param("lokacija") String lokacija);

    @Query("Select k from Klinika k where UPPER(k.adresa) LIKE %:lokacija% AND :ocena <= (SELECT AVG(oc.ocena) FROM OcenaKlinike oc WHERE k.id = oc.klinika)")
    List<Klinika> findKlinikaByAdresaAndOcena(@Param("lokacija") String lokacija, @Param("ocena") Double ocena);

    Optional<Klinika> findById(Long id);

}
