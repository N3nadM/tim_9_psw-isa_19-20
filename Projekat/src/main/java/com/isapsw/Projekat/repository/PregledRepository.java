package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PregledRepository extends JpaRepository<Pregled, Long> {

    Optional<Pregled> findById(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    @Query("SELECT p FROM Pregled p WHERE p.id = :id")
    Optional<Pregled> findByIdTransaction(@Param("id") Long id);

    @Query("SELECT p FROM Pregled p WHERE CAST(p.datumPocetka AS date) = CAST(:datum AS date)")
    List<Pregled> findByPregledDatumPocetka(Date datum);

    List<Pregled> findPregledByPacijentId(Long id);

    @Query("SELECT p FROM Pregled p WHERE p.pacijent.id = :id AND p.sala is not null")
    List<Pregled> findPregledByPacijentIdWhereSalaIdIsNotNull(@Param("id") Long id);

    List<Pregled> findPregledByLekarId(Long id);

    List<Pregled> findPregledsByLekarIdAndStanje(Long id, int stanje);

    List<Pregled> findPregledsByMedicinskaSestraId(Long id);

    List<Pregled> findPregledBySalaId(Long id);

    @Query("SELECT p FROM Pregled p WHERE p.sala.klinika.id = :klinikaId AND p.pacijent.id is null")
    List<Pregled> findPregledBySalaKlinikaId(@Param("klinikaId") Long klinikaId);

    @Query("SELECT p FROM Pregled p WHERE p.lekar.id = :lekarId AND p.pacijent.id = :pacijentId AND p.datumZavrsetka < :datum")
    List<Pregled> findPregledByPacijentIdAndLekarId(@Param("lekarId") Long lekarId, @Param("pacijentId")Long pacijentId, @Param("datum") Date datum);

    @Query("SELECT p FROM Pregled p WHERE p.pacijent.id = :pacijentId AND CAST(p.datumZavrsetka AS date) = CAST(:datum AS date)")
    List<Pregled> findPregledByDatumPac(@Param("pacijentId")Long pacijentId, @Param("datum") Date datum);

    @Query("SELECT p FROM Pregled p WHERE p.medicinskaSestra.id = :id AND p.datumPocetka BETWEEN :datum AND :tomorrow")
    List<Pregled> findSestraPreglediDatum(@Param("id") Long id, @Param("datum") Date datum, @Param("tomorrow") Date tomorrow);

    @Query("SELECT p FROM Pregled p WHERE p.lekar.id = :id AND p.datumPocetka BETWEEN :datum AND :tomorrow")
    List<Pregled> findLekarPreglediDatum(@Param("id") Long id, @Param("datum") Date datum, @Param("tomorrow") Date tomorrow);

    @Query("SELECT DISTINCT p.tipPregleda FROM Pregled p LEFT JOIN TipPregleda tp ON tp.id = p.tipPregleda.id WHERE tp.klinika.id = :id AND p.datumPocetka > :date")
    List<TipPregleda> findTipoveKojiImajuZakazanePreglede(@Param("id") Long id, @Param("date") Date date);

    @Query("SELECT DISTINCT p.sala FROM Pregled p LEFT JOIN Sala s ON s.id = p.sala.id WHERE s.klinika.id = :id AND p.datumPocetka > :date")
    List<Sala> findSaleUKojimaImaZakazanihPregleda(@Param("id") Long id, @Param("date") Date date);

    @Query("SELECT DISTINCT p.lekar FROM Pregled p LEFT JOIN Lekar l ON l.id = p.lekar.id WHERE l.klinika.id = :id AND p.datumPocetka > :date")
    List<Lekar> findLekareKodKojihImaZakazanihPregleda(@Param("id") Long id, @Param("date") Date date);

    @Query("SELECT DISTINCT p.pacijent FROM Pregled p WHERE p.lekar.korisnik.id = :idLekar AND p.pacijent.korisnik.id = :idPacijent AND p.stanje <> 0" )
    List<Pacijent> proveraPregled(@Param("idLekar") Long idLekar, @Param("idPacijent") Long idPacijent);

    @Query("SELECT DISTINCT p.pacijent FROM Pregled p WHERE p.medicinskaSestra.korisnik.id = :idSestra AND p.pacijent.korisnik.id = :idPacijent AND p.stanje <> 0")
    List<Pacijent> proveraPregledSestra(@Param("idSestra") Long idSestra, @Param("idPacijent") Long idPacijent);

    @Query("SELECT DISTINCT p FROM Pregled p WHERE p.lekar.klinika.id = :idKlinika AND p.sala is null AND p.datumPocetka > current_date ")
    List<Pregled> preglediKojiNemajuSalu(@Param("idKlinika") Long idKlinika);

    @Query("SELECT DISTINCT p FROM Pregled p WHERE p.tipPregleda.id = :idTip AND p.datumPocetka BETWEEN :datumOd AND :datumDo ")
    List<Pregled> zaRacunanjePrihoda(@Param("idTip") Long idTip, @Param("datumOd") Date datumOd, @Param("datumDo") Date datumDo);

}
