package com.isapsw.Projekat.Rezervacije.service;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.PregledDTO;

import com.isapsw.Projekat.repository.*;
import com.isapsw.Projekat.service.EmailService;
import com.isapsw.Projekat.service.PregledService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.mockito.ArgumentMatchers.anyString;

import javax.mail.MessagingException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
public class PregledServiceTest {

    @MockBean
    private EmailService emailService;

    @MockBean
    private JavaMailSender javaMailSendermock;

    @Autowired
    PregledService pregledService;

    @MockBean
    PregledRepository pregledRepository;

    @MockBean
    TipPregledaRepository tipPregledaRepository;

    @MockBean
    TipoviPregledaRepository tipoviPregledaRepository;

    @MockBean
    LekarRepository lekarRepository;

    @MockBean
    SalaRepository salaRepository;

    @MockBean
    MedSestraRepository medSestraRepository;

    @MockBean
    PacijentRepository pacijentRepository;

    @Before
    public void setup() throws ParseException {
        TipPregleda tp = new TipPregleda();
        tp.setCenaOperacije(1200);
        tp.setCenaPregleda(1200);
        tp.setNaziv("Kardiovaskularni");
        Pregled pregled = new Pregled();
        pregled.setTipPregleda(tp);

        TipPregleda tipPregleda = new TipPregleda();
        tipPregleda.setMinimalnoTrajanjeMin(30);

        given(
                tipPregledaRepository.findById(1L)
        ).willReturn(
                Optional.of(tipPregleda)
        );

    }

    @Test
    public void addPregled() throws ParseException {
        Klinika klinika = new Klinika();
        klinika.setNaziv("Klinika 1");
        klinika.setAdresa("Pasiceva 34, Novi Sad");
        klinika.setOpis("Jako fina klinika");

        PregledDTO pregledDTO = new PregledDTO();

        pregledDTO.setLekarId("1");
        pregledDTO.setMedSestraId("1");
        pregledDTO.setSalaId("1");
        pregledDTO.setTipPregledaId("1");
        pregledDTO.setDatum("06-feb-2020 13:30:00");
        pregledDTO.setPopust("0");

        Pregled pregled = new Pregled();

        Korisnik k3 = new Korisnik();
        k3.setGrad("Novi Sad");
        k3.setTelefon("Telefon11");
        k3.setAdresa("adresa23");
        k3.setDrzava("Srbija");
        k3.setIme("Rajna");
        k3.setPrezime("Kulic");
        k3.setPassword("JakPass");
        k3.setEmail("rajna@gmail.com");
        Pacijent pacijent = new Pacijent();
        pacijent.setKorisnik(k3);

        pregled.setPacijent(pacijent);

        Korisnik k1 = new Korisnik();
        k1.setGrad("Novi Sad");
        k1.setTelefon("Telefon");
        k1.setAdresa("adresa");
        k1.setDrzava("Srbija");
        k1.setIme("Milenko");
        k1.setPrezime("Savic");
        k1.setPassword("JakPass");
        k1.setEmail("milenkoXX@gmail.com");

        Lekar lekar = new Lekar();
        lekar.setKorisnik(k1);
        lekar.setId(1L);

        Sala sala = new Sala();
        sala.setId(1L);
        sala.setSalaIdentifier("1-1");

        TipPregleda tp = new TipPregleda();
        tp.setId(1L);
        tp.setMinimalnoTrajanjeMin(30);

        Korisnik k2 = new Korisnik();
        k2.setGrad("Novi Sad");
        k2.setTelefon("Telefon11");
        k2.setAdresa("adresa11");
        k2.setDrzava("Srbija");
        k2.setIme("Nevenko");
        k2.setPrezime("Savic");
        k2.setPassword("JakPass");
        k2.setEmail("nevenXX@gmail.com");

        MedicinskaSestra medicinskaSestra = new MedicinskaSestra();
        medicinskaSestra.setKorisnik(k2);
        medicinskaSestra.setId(1L);

        when(pregledRepository.save(any(Pregled.class))).thenReturn(pregled);
        when(lekarRepository.findLekarById(1L)).thenReturn(lekar);
        when(salaRepository.getOne(1L)).thenReturn(sala);
        when(tipoviPregledaRepository.getOne(1L)).thenReturn(tp);
        when(medSestraRepository.getOne(1L)).thenReturn(medicinskaSestra);

        Pregled ret = pregledService.addPregled(pregledDTO);

        assertEquals(ret, pregled);

    }

    @Test
    public void findPreglediZaSestru() throws ParseException {
        List<Pregled> expected = new ArrayList<>();

        expected.add(new Pregled());
        expected.add(new Pregled());
        expected.add(new Pregled());

        Date datum = new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-20");
        Date tomorrow =new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-20");
        tomorrow.setTime(datum.getTime() + (1000 * 60 * 60 * 24));
        when(pregledRepository.findSestraPreglediDatum(1L, datum, tomorrow)).thenReturn(expected);

        List<Pregled> pregledi = pregledService.findPreglediZaSestru("1", "2020-02-20");

        assertEquals(pregledi, expected);
        assertEquals(3, pregledi.size());
    }

    @Test
    public void findPreglediZaLekara() throws ParseException {
        List<Pregled> expected = new ArrayList<>();

        expected.add(new Pregled());
        expected.add(new Pregled());
        expected.add(new Pregled());
        expected.add(new Pregled());
        expected.add(new Pregled());

        Date datum = new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-20");
        Date tomorrow =new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-20");
        tomorrow.setTime(datum.getTime() + (1000 * 60 * 60 * 24));
        when(pregledRepository.findLekarPreglediDatum(1L, datum, tomorrow)).thenReturn(expected);

        List<Pregled> pregledi = pregledService.findPreglediZaLekara("1", "2020-02-20");

        assertEquals(pregledi, expected);
        assertEquals(5, pregledi.size());
    }

    @Test
    public void findPreglediZaSestruOdmor() throws ParseException {
        List<Pregled> expected = new ArrayList<>();

        expected.add(new Pregled());
        expected.add(new Pregled());
        expected.add(new Pregled());
        expected.add(new Pregled());
        expected.add(new Pregled());

        Date datum = new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-20");
        Date tomorrow =new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-21");
        tomorrow.setTime(tomorrow.getTime() + (1000 * 60 * 60 * 24));
        when(pregledRepository.findSestraPreglediDatum(1L, datum, tomorrow)).thenReturn(expected);

        List<Pregled> pregledi = pregledService.findPreglediZaSestruOdmor("1", "2020-02-20", "2020-02-21");

        System.out.println("Exs" + expected.size() + " a " + pregledi.size());
        assertEquals(pregledi, expected);
        assertEquals(5, pregledi.size());
    }

    @Test
    public void findPreglediZaLekaraOdmor() throws ParseException {
        List<Pregled> expected = new ArrayList<>();

        expected.add(new Pregled());
        expected.add(new Pregled());
        expected.add(new Pregled());
        expected.add(new Pregled());
        expected.add(new Pregled());

        Date datum = new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-20");
        Date tomorrow =new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-23");
        tomorrow.setTime(tomorrow.getTime() + (1000 * 60 * 60 * 24));
        when(pregledRepository.findLekarPreglediDatum(1L, datum, tomorrow)).thenReturn(expected);

        List<Pregled> pregledi = pregledService.findPreglediZaLekaraOdmor("1","2020-02-20", "2020-02-23");

        assertEquals(expected,pregledi);
        assertEquals(5, pregledi.size());
        verify(pregledRepository, times(1)).findLekarPreglediDatum(1L, datum, tomorrow);
        verifyNoMoreInteractions(pregledRepository);
    }

    @Test
    public void getZavrseniPreglediByLekarId() throws ParseException {
        List<Pregled> expected = new ArrayList<>();

        expected.add(new Pregled());
        expected.add(new Pregled());

        when(pregledRepository.findPregledsByLekarIdAndStanje(1L, 2)).thenReturn(expected);
        List<Pregled> pregledi = pregledService.getZavrseniPreglediByLekarId(1L);

        assertEquals(expected,pregledi);
        assertEquals(2, pregledi.size());

        verify(pregledRepository, times(1)).findPregledsByLekarIdAndStanje(1L, 2);
        verifyNoMoreInteractions(pregledRepository);
    }

    @Test
    public void getPreglediKojiNemajuSalu() throws ParseException {
        List<Pregled> expected = new ArrayList<>();

        expected.add(new Pregled());
        expected.add(new Pregled());

        when(pregledRepository.preglediKojiNemajuSalu(1L)).thenReturn(expected);
        List<Pregled> pregledi = pregledService.getPreglediKojiNemajuSalu("1");

        assertEquals(expected,pregledi);
        assertEquals(2, pregledi.size());

        verify(pregledRepository, times(1)).preglediKojiNemajuSalu(1L);
        verifyNoMoreInteractions(pregledRepository);
    }

    @Test
    @Transactional
    public void sacuvajPregled() throws ParseException, MessagingException, InterruptedException {
        Pregled pregled = new Pregled();
        pregled.setId(1L);
        pregled.setDatumPocetka(new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("28-feb-2020 12:20:23"));
        Korisnik k1 = new Korisnik();
        k1.setGrad("Novi Sad");
        k1.setTelefon("Telefon");
        k1.setAdresa("adresa");
        k1.setDrzava("Srbija");
        k1.setIme("Milenko");
        k1.setPrezime("Savic");
        k1.setPassword("JakPass");
        k1.setEmail("milenkoXX@gmail.com");

        Lekar lekar = new Lekar();
        lekar.setKorisnik(k1);
        lekar.setId(1L);

        Korisnik k2 = new Korisnik();
        k2.setGrad("Novi Sad");
        k2.setTelefon("Telefon11");
        k2.setAdresa("adresa11");
        k2.setDrzava("Srbija");
        k2.setIme("Nevenko");
        k2.setPrezime("Savic");
        k2.setPassword("JakPass");
        k2.setEmail("nevenXX@gmail.com");

        MedicinskaSestra medicinskaSestra = new MedicinskaSestra();
        medicinskaSestra.setKorisnik(k2);



        Sala sala = new Sala();
        sala.setSalaIdentifier("1-1");

        Korisnik k3 = new Korisnik();
        k3.setGrad("Novi Sad");
        k3.setTelefon("Telefon11");
        k3.setAdresa("adresa23");
        k3.setDrzava("Srbija");
        k3.setIme("Rajna");
        k3.setPrezime("Kulic");
        k3.setPassword("JakPass");
        k3.setEmail("rajna@gmail.com");
        Pacijent pacijent = new Pacijent();
        pacijent.setKorisnik(k3);
        Klinika klinika = new Klinika();
        klinika.setNaziv("Klinika");
        klinika.setAdresa("adresa");

        pregled.setPacijent(pacijent);

        Date date = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("28-feb-2020 12:20:23");
        when(pregledRepository.getOne(1L)).thenReturn(pregled);
        when(pregledRepository.getPacijent(1L)).thenReturn(pacijent);
        when(lekarRepository.findByIdTransaction(1L)).thenReturn(Optional.of(lekar));
        when(salaRepository.findByIdTransaction(1L)).thenReturn(Optional.of(sala));
        when(medSestraRepository.findById(1L)).thenReturn(Optional.of(medicinskaSestra));
        when(tipoviPregledaRepository.getMinimalnoTrajanje(1L)).thenReturn(60);
        when(lekarRepository.getKlinika(1L)).thenReturn(klinika);

        Mockito.doNothing().when(emailService).sendOsobljePregledRezervacijaSale("admin@gmail.com", date , "1-1");
        Mockito.doNothing().when(emailService).sendOsobljePregledRezervacijaSale("nevenXX@gmail.com", date , "1-1");
        Mockito.doNothing().when(emailService).sendPacijentPregledRezervacijaSale("rajna@gmail.com", date , "Klinika", "adresa", "1-1");
        Pregled pr = pregledService.sacuvajPregled("1", "1", "1", "1", "28-feb-2020 12:20:23");

        assertEquals(pr, pregled);
    }

    @Test
    @Transactional
    public void zakaziPredefinisaniPregled() throws ParseException {
        Pregled pregled = new Pregled();
        pregled.setId(1L);
        pregled.setDatumPocetka(new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("28-feb-2020 12:20:23"));
        pregled.setDatumZavrsetka(new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("28-feb-2020 12:50:23"));


        Korisnik k3 = new Korisnik();
        k3.setGrad("Novi Sad");
        k3.setTelefon("Telefon11");
        k3.setAdresa("adresa23");
        k3.setDrzava("Srbija");
        k3.setIme("Rajna");
        k3.setPrezime("Kulic");
        k3.setPassword("JakPass");
        k3.setEmail("rajna@gmail.com");
        Pacijent pacijent = new Pacijent();
        pacijent.setKorisnik(k3);
        k3.setId(1L);
        Klinika klinika = new Klinika();
        klinika.setNaziv("Klinika");
        klinika.setAdresa("adresa");
        pregled.setVersion(1L);

        pregled.setPacijent(pacijent);

        when(pregledRepository.getOne(1L)).thenReturn(pregled);
        when(pacijentRepository.findPacijentByKorisnikId(1L)).thenReturn(pacijent);

        Boolean ret = pregledService.zakaziPredefinisaniPregled(1L, "1", "1");
        assertEquals(true, ret);
    }

    @Test
    public void getPregledById() {
        Pregled pregled = new Pregled();
        when(pregledRepository.getOne(1L)).thenReturn(pregled);
        Pregled pr = pregledService.getPregledById(1L);
        assertEquals(pr, pregled);

    }

    @Test
    public void getPreglediByPacijentId() {
        Pregled pregled = new Pregled();
        pregled.setIzvestaj("Fino fino");
        when(pregledRepository.findPregledByPacijentIdWhereSalaIdIsNotNull(1L)).thenReturn(Arrays.asList(pregled));
        List<Pregled> pr = pregledService.getPreglediByPacijentId(1L);
        assertEquals("Fino fino", pr.get(0).getIzvestaj());
    }

    @Test
    public void getPreglediByLekarId() {
        Pregled pregled = new Pregled();
        pregled.setIzvestaj("Odlicno");
        when(pregledRepository.findPregledByLekarId(1L)).thenReturn(Arrays.asList(pregled));
        List<Pregled> pr = pregledService.getPreglediByLekarId(1L);
        assertEquals("Odlicno", pr.get(0).getIzvestaj());
    }

    @Test
    public void getPreglediByMedSestraId() {
        Pregled pregled = new Pregled();
        pregled.setIzvestaj("Odlicno");
        when(pregledRepository.findPregledsByMedicinskaSestraId(1L)).thenReturn(Arrays.asList(pregled));
        List<Pregled> pr = pregledService.getPreglediByMedSestraId(1L);
        assertEquals("Odlicno", pr.get(0).getIzvestaj());
    }

    @Test
    public void getPreglediBySalaId() {
        Pregled pregled = new Pregled();
        pregled.setIzvestaj("Odlicno");
        when(pregledRepository.findPregledBySalaId(1L)).thenReturn(Arrays.asList(pregled));
        List<Pregled> pr = pregledService.getPreglediBySalaId(1L);
        assertEquals("Odlicno", pr.get(0).getIzvestaj());
    }

    @Test
    public void getPredefinisaniPregledi() {
        Pregled pregled = new Pregled();
        pregled.setIzvestaj("Odlicno");
        when(pregledRepository.findPregledBySalaKlinikaId(1L)).thenReturn(Arrays.asList(pregled));
        List<Pregled> pr = pregledService.getPredefinisaniPregledi(1L);
        assertEquals("Odlicno", pr.get(0).getIzvestaj());
    }
}
