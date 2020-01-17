package com.isapsw.Projekat.repository;

import com.isapsw.Projekat.domain.Operacija;
import com.isapsw.Projekat.domain.Pregled;
import com.isapsw.Projekat.domain.Sala;
import com.isapsw.Projekat.domain.TipPregleda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OperacijaRepository extends JpaRepository<Operacija, Long> {
    List<Operacija> findOperacijeByPacijentId(Long id);

    List<Operacija> findOperacijasByMedicinskaSestraId(Long id);

    List<Operacija> findOperacijasBySalaId(Long id);

    @Query("SELECT o FROM Operacija o LEFT JOIN o.lekari l WHERE l.id = :id")
    List<Operacija> findOperacijasByLekari(@Param("id") Long id);

    @Query("SELECT o FROM Operacija o LEFT JOIN o.lekari l WHERE l.id = :id AND o.stanje = :stanje")
    List<Operacija> findOperacijasByLekariAndStanje(@Param("id") Long id, @Param("stanje") int stanje);

    @Query("SELECT o FROM Operacija o LEFT JOIN o.lekari l WHERE l.id = :lekarId AND o.pacijent.id = :pacijentId AND o.datumZavrsetka < :datum")
    List<Operacija> findOperacijeByPacijentIdAndLekarId(@Param("lekarId") Long lekarId, @Param("pacijentId")Long pacijentId, @Param("datum") Date datum);

    @Query("SELECT o FROM Operacija o WHERE o.medicinskaSestra.id = :id AND o.datumPocetka BETWEEN :datum AND :tomorrow")
    List<Operacija> findSestraOperacijeDatum(@Param("id") Long id, @Param("datum") Date datum, @Param("tomorrow") Date tomorrow);

    @Query("SELECT o FROM Operacija o WHERE o.datumPocetka BETWEEN :datum AND :tomorrow")
    List<Operacija> findOperacijeDatum(@Param("datum") Date datum, @Param("tomorrow") Date tomorrow);

    @Query("SELECT DISTINCT o.tipPregleda FROM Operacija o LEFT JOIN TipPregleda tp ON tp.id = o.tipPregleda.id WHERE tp.klinika.id = :id AND o.datumPocetka > :date")
    List<TipPregleda> findTipoveKojiImajuZakazaneOperacije(@Param("id") Long id, @Param("date") Date date);

    @Query("SELECT DISTINCT o.sala FROM Operacija o LEFT JOIN Sala s ON s.id = o.sala.id WHERE s.klinika.id = :id AND o.datumPocetka > :date")
    List<Sala> findSaleUKojimaImaZakazanihOperacija(@Param("id") Long id, @Param("date") Date date);

    @Query("SELECT DISTINCT o FROM Operacija o WHERE o.sala.klinika.id = :id AND o.datumPocetka> :date")
    List<Operacija> findBuduceOperacijeNaKlinici(@Param("id") Long id, @Param("date") Date date);

    @Query("SELECT DISTINCT o FROM Operacija o WHERE o.tipPregleda.klinika.id = :klinikaId AND o.sala is null AND o.datumPocetka> current_date ")
    List<Operacija> operacijeKojeNemajuSalu(@Param("klinikaId") Long klinikaId);

    @Query("SELECT DISTINCT o FROM Operacija o WHERE o.tipPregleda.id = :tip AND o.datumPocetka BETWEEN :datumOd AND :datumDo")
    List<Operacija> zaRacunanjePrihoda(@Param("tip") Long tip, @Param("datumOd") Date datumOd, @Param("datumDo") Date datumDo);

}
