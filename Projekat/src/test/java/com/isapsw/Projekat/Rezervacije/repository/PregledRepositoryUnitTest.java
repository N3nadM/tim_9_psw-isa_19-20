package com.isapsw.Projekat.Rezervacije.repository;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.repository.PregledRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PregledRepositoryUnitTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PregledRepository pregledRepository;

    @Before
    public void setUp() {
        Korisnik k1 = new Korisnik();
        k1.setGrad("Novi Sad");
        k1.setTelefon("Telefon");
        k1.setAdresa("adresa");
        k1.setDrzava("Srbija");
        k1.setIme("Milenko");
        k1.setPrezime("Savic");
        k1.setPassword("JakPass");
        k1.setEmail("milenkoXX@gmail.com");

        Korisnik k2 = new Korisnik();
        k2.setGrad("Novi Sad");
        k2.setTelefon("Telefon11");
        k2.setAdresa("adresa11");
        k2.setDrzava("Srbija");
        k2.setIme("Nevenko");
        k2.setPrezime("Savic");
        k2.setPassword("JakPass");
        k2.setEmail("nevenXX@gmail.com");

        Korisnik k3 = new Korisnik();
        k3.setGrad("Novi Sad");
        k3.setTelefon("Telefon11");
        k3.setAdresa("adresa23");
        k3.setDrzava("Srbija");
        k3.setIme("Rajna");
        k3.setPrezime("Kulic");
        k3.setPassword("JakPass");
        k3.setEmail("rajna@gmail.com");

        entityManager.persist(k1);
        entityManager.persist(k2);
        entityManager.persist(k3);

        //p1-pacijent postoji
        Pregled p1 = new Pregled();
        //p2-pacijent ne postoji
        Pregled p2 = new Pregled();

        Pacijent pac = new Pacijent();
        pac.setKorisnik(k1);
        pac.setJbzo("0123123");
        entityManager.persist(pac);

        Klinika klinika = new Klinika();
        klinika.setAdresa("Adresa");
        klinika.setNaziv("Lepa klinika");
        entityManager.persist(klinika);

        TipPregleda tp = new TipPregleda();
        tp.setCenaPregleda(1200);
        tp.setCenaOperacije(1200);
        tp.setNaziv("Kardiovaskularni");
        tp.setKlinika(klinika);

        entityManager.persist(tp);

        Sala sala = new Sala();
        sala.setNaziv("1");
        sala.setSalaIdentifier("1 - 1213");
        sala.setKlinika(klinika);

        entityManager.persist(sala);

        Lekar lekar = new Lekar();
        lekar.setKlinika(klinika);
        lekar.setTipPregleda(tp);
        lekar.setKorisnik(k2);

        entityManager.persist(lekar);

        MedicinskaSestra medicinskaSestra = new MedicinskaSestra();
        medicinskaSestra.setKlinika(klinika);
        medicinskaSestra.setKorisnik(k3);

        entityManager.persist(medicinskaSestra);

        p1.setMedicinskaSestra(medicinskaSestra);
        p1.setPacijent(pac);
        p1.setTipPregleda(tp);
        p1.setSala(sala);
        p1.setLekar(lekar);
        p1.setIzvestaj("Svaka cast");
        p1.setDatumPocetka(new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime());
        p1.setDatumZavrsetka(new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime());
        p1.setStanje(2);

        p2.setTipPregleda(tp);
        p2.setSala(sala);
        p2.setLekar(lekar);
        p2.setStanje(1);
        p2.setMedicinskaSestra(medicinskaSestra);

        entityManager.persist(p1);
        entityManager.persist(p2);
    }

    //Vraca pregled po id-u
    @Test
    public void testFindByIdTransaction() {
        Pregled pregled = pregledRepository.findByIdTransaction(1L).get();

        assertEquals("Svaka cast", pregled.getIzvestaj());
    }

    //Pronalazi pregled po datumu
    @Test
    public void findByPregledDatumPocetka() {
        Date date = new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime();
        List<Pregled> pregledi = pregledRepository.findByPregledDatumPocetka(date);

        assertEquals(1, pregledi.size());
    }

    //Vraca listu pregleda koji imaju dodeljenu salu
    @Test
    public void testFindPregledByPacijentIdWhereSalaIdIsNotNull() {
        List<Pregled> pregledi = pregledRepository.findPregledByPacijentIdWhereSalaIdIsNotNull(1L);

        assertEquals(1, pregledi.size());
    }

    //Vraca listu pregleda po kriterijum pretrage id-a klinike kojoj neka sala pripada gde pacijent ne postoji
    @Test
    public void testFindPregledBySalaKlinikaId() {
        List<Pregled> pregledi = pregledRepository.findPregledBySalaKlinikaId(1L);

        assertEquals(1, pregledi.size());
    }

    //Vraca listu pregleda koji su datume pre definisanog parametra datuma gde se gleda id pacijenta i lekara
    @Test
    public void findPregledByPacijentIdAndLekarId() {
        List<Pregled> pregledi = pregledRepository.findPregledByPacijentIdAndLekarId(1L, 1L, new GregorianCalendar(2020, Calendar.FEBRUARY, 19).getTime());

        assertEquals(1, pregledi.size());
    }

    @Test
    public void findPregledByLekarId() {
        List<Pregled> pregledi = pregledRepository.findPregledByLekarId(1L);

        assertEquals(2, pregledi.size());
    }

    @Test
    public void findPregledsByLekarIdAndStanje() {
        List<Pregled> pregledi = pregledRepository.findPregledsByLekarIdAndStanje(1L, 1);

        assertEquals("Kardiovaskularni", pregledi.get(0).getTipPregleda().getNaziv());
        assertEquals(1, pregledi.size());
    }

    @Test
    public void findPregledsByMedicinskaSestraId() {
        List<Pregled> pregledi = pregledRepository.findPregledsByMedicinskaSestraId(1L);
        assertEquals(2, pregledi.size());
    }

    @Test
    public void findPregledBySalaId() {
        List<Pregled> pregledi = pregledRepository.findPregledBySalaId(1L);
        assertEquals(2, pregledi.size());
        assertEquals("1 - 1213", pregledi.get(0).getSala().getSalaIdentifier());
    }

    //Vraca preglede pacijenta sa odredjenim id-em za odredjen datum
    @Test
    public void findPregledByDatumPac() {
        List<Pregled> pregledi = pregledRepository.findPregledByDatumPac(1L, new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime());
        assertEquals(1, pregledi.size());
        assertEquals("0123123", pregledi.get(0).getPacijent().getJbzo());
    }

    //Vraca preglede dodeljene medicinskoj sestri koji se nalaze izmedju dva datuma
    @Test
    public void findSestraPreglediDatum() {
        List<Pregled> pregledi = pregledRepository.findSestraPreglediDatum(1L,new GregorianCalendar(2020, Calendar.FEBRUARY, 10).getTime(), new GregorianCalendar(2020, Calendar.FEBRUARY, 12).getTime() );

        assertEquals(1, pregledi.size());
        assertEquals("Lepa klinika", pregledi.get(0).getMedicinskaSestra().getKlinika().getNaziv());
    }

    //Vraca preglede dodeljene lekaru koji se nalaze izmedju dva datuma
    @Test
    public void findLekarPreglediDatum() {
        List<Pregled> pregledi = pregledRepository.findLekarPreglediDatum(1L,new GregorianCalendar(2020, Calendar.FEBRUARY, 10).getTime(), new GregorianCalendar(2020, Calendar.FEBRUARY, 12).getTime() );

        assertEquals(1, pregledi.size());
        assertEquals("Lepa klinika", pregledi.get(0).getLekar().getKlinika().getNaziv());
    }

    //Vraca tipove pregleda za koje postoji predstojeci zakazan pregled u odnosu na poslati datum
    @Test
    public void findTipoveKojiImajuZakazanePreglede() {
        List<TipPregleda> tipPregledas = pregledRepository.findTipoveKojiImajuZakazanePreglede(1L, new GregorianCalendar(2020, Calendar.FEBRUARY, 10).getTime());

        assertEquals(1, tipPregledas.size());
        assertEquals("Kardiovaskularni", tipPregledas.get(0).getNaziv());
    }


    //Vraca sale za koje postoji predstojeci zakazan pregled u odnosu na poslati datum
    @Test
    public void findSaleUKojimaImaZakazanihPregleda() {
        List<Sala> salas = pregledRepository.findSaleUKojimaImaZakazanihPregleda(1L, new GregorianCalendar(2020, Calendar.FEBRUARY, 10).getTime());

        assertEquals(1, salas.size());
        assertEquals("1 - 1213", salas.get(0).getSalaIdentifier());
    }


    //Vraca lekare trazene klinike za koje postoji predstojeci zakazan pregled u odnosu na poslati datum
    @Test
    public void findLekareKodKojihImaZakazanihPregleda() {
        List<Lekar> lekars = pregledRepository.findLekareKodKojihImaZakazanihPregleda(1L, new GregorianCalendar(2020, Calendar.FEBRUARY, 10).getTime());

        assertEquals(1, lekars.size());
        assertEquals("Lepa klinika", lekars.get(0).getKlinika().getNaziv());
    }

    @Test
    public void proveraPregled() {
        List<Pacijent> pacijenti = pregledRepository.proveraPregled(2L, 1L);

        assertEquals(1, pacijenti.size());
        assertEquals("Milenko", pacijenti.get(0).getKorisnik().getIme());
        assertEquals("Savic", pacijenti.get(0).getKorisnik().getPrezime());
        assertEquals("Srbija", pacijenti.get(0).getKorisnik().getDrzava());
    }

    @Test
    public void proveraPregledSestra() {
        List<Pacijent> pacijenti = pregledRepository.proveraPregledSestra(3L, 1L);

        assertEquals(1, pacijenti.size());
        assertEquals("Milenko", pacijenti.get(0).getKorisnik().getIme());
        assertEquals("Savic", pacijenti.get(0).getKorisnik().getPrezime());
        assertEquals("Srbija", pacijenti.get(0).getKorisnik().getDrzava());
        assertEquals("adresa", pacijenti.get(0).getKorisnik().getAdresa());
        assertEquals("milenkoXX@gmail.com", pacijenti.get(0).getKorisnik().getEmail());
    }

    //Vraca predstojece preglede odredjene klinike koji nemaju dodeljenu salu
    @Test
    public void preglediKojiNemajuSalu() {
        List<Pregled> pregledi = pregledRepository.preglediKojiNemajuSalu(1L);
        assertEquals(0, pregledi.size());
    }

    @Test
    public void zaRacunanjePrihoda() {
        List<Pregled> pregledi = pregledRepository.zaRacunanjePrihoda(1L, new GregorianCalendar(2020, Calendar.FEBRUARY, 9).getTime(), new GregorianCalendar(2020, Calendar.FEBRUARY, 19).getTime());
        assertEquals(1, pregledi.size());
        assertEquals("Kardiovaskularni", pregledi.get(0).getTipPregleda().getNaziv());

    }
}
