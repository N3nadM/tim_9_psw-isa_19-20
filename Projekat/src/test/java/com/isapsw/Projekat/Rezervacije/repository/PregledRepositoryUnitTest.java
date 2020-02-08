package com.isapsw.Projekat.Rezervacije.repository;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.repository.PregledRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PregledRepositoryUnitTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PregledRepository pregledRepository;

    @Before
    public void setUp() {
        //p1-pacijent postoji
        Pregled p1 = new Pregled();
        //p2-pacijent ne postoji
        Pregled p2 = new Pregled();

        Pacijent pac = new Pacijent();
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

        entityManager.persist(lekar);

        MedicinskaSestra medicinskaSestra = new MedicinskaSestra();
        medicinskaSestra.setKlinika(klinika);

        entityManager.persist(medicinskaSestra);

        p1.setMedicinskaSestra(medicinskaSestra);
        p1.setPacijent(pac);
        p1.setTipPregleda(tp);
        p1.setSala(sala);
        p1.setLekar(lekar);
        p1.setIzvestaj("Svaka cast");
        p1.setDatumPocetka(new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime());
        p1.setDatumZavrsetka(new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime());

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

//        assertEquals("Kardiovaskularni", pregledi.get(0).getTipPregleda().getNaziv());
        assertEquals(1, pregledi.size());
    }

    @Test
    public void findPregledsByMedicinskaSestraId() {
        List<Pregled> pregledi = pregledRepository.findPregledsByMedicinskaSestraId(1L);
        assertEquals(2, pregledi.size());
    }
}
