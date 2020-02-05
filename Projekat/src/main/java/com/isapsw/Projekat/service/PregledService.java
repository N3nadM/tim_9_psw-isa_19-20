package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.PregledDTO;
import com.isapsw.Projekat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@Service
public class PregledService {

    @Autowired
    private PregledRepository pregledRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private LekarRepository lekarRepository;

    @Autowired
    private TipoviPregledaRepository tipoviPregledaRepository;

    @Autowired
    private MedSestraRepository medSestraRepository;

    @Autowired
    private PacijentRepository pacijentRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AdminKlinikeRepository adminKlinikeRepository;

    public Pregled getPregledById(Long id){
        return pregledRepository.findById(id).get();
    }

    public List<Pregled> getPreglediByPacijentId(Long id) {
        return pregledRepository.findPregledByPacijentIdWhereSalaIdIsNotNull(id);
    }

    public List<Pregled> getPreglediByLekarId(Long id) {
        return pregledRepository.findPregledByLekarId(id);
    }

    public List<Pregled> getPreglediByMedSestraId(Long id){ return pregledRepository.findPregledsByMedicinskaSestraId(id); }

    public List<Pregled> getPreglediBySalaId(Long id) { return pregledRepository.findPregledBySalaId(id); }

    public List<Pregled> getPredefinisaniPregledi(Long id) {
       return pregledRepository.findPregledBySalaKlinikaId(id);

    }

    public Pregled addPregled(PregledDTO pregledDTO) throws ParseException {
        Pregled pregled = new Pregled();
        Optional<Sala> sala = salaRepository.findById(Long.parseLong(pregledDTO.getSalaId()));
        pregled.setSala(sala.get());
        Lekar lekar = lekarRepository.findLekarById(Long.parseLong(pregledDTO.getLekarId()));
        pregled.setLekar(lekar);

        Optional<TipPregleda> tp = tipoviPregledaRepository.findById(Long.parseLong(pregledDTO.getTipPregledaId()));
        pregled.setTipPregleda(tp.get());

        pregled.setMedicinskaSestra(medSestraRepository.findById(Long.parseLong(pregledDTO.getMedSestraId())).get());
        pregled.setPacijent(null);

        Date datum = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(pregledDTO.getDatum());
        Date d2 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(pregledDTO.getDatum());
        pregled.setDatumPocetka(datum);

        d2.setTime(d2.getTime() + tp.get().getMinimalnoTrajanjeMin() * 60 * 1000);
        pregled.setDatumZavrsetka(d2);

        Date date = Calendar.getInstance().getTime();
        pregled.setDatumKreiranja(date);
        pregled.setPopust(Integer.parseInt(pregledDTO.getPopust()));

        pregled.setIzvestaj("");
        return pregledRepository.save(pregled);
    }

    @Transactional
    public Boolean zakaziPregled(Long korisnikId, String lekarId, String datum) throws ParseException {
        Lekar lekar = lekarRepository.findLekarById(Long.parseLong(lekarId));

        Date date = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(datum);

        //ovde bi mozda trebalo staviti upit u bazu umesto for petlje
        for(int i = 0; i < lekar.getPregledi().size(); i++) {
            if(date.getTime() == lekar.getPregledi().get(i).getDatumPocetka().getTime()) {
                return false;
            }
        }

        //trebala bi da postoji provera i za operacije
        Pacijent pacijent = pacijentRepository.findPacijentByKorisnikId(korisnikId);
        List<Pregled> pregledi = pregledRepository.findPregledByDatumPac(pacijent.getId(), date);
        for (Pregled p: pregledi) {

            long pocetakStari = p.getDatumPocetka().getTime();
            long krajStari = p.getDatumZavrsetka().getTime();
            long pocetakNovi = date.getTime();
            long krajNovi = date.getTime() + lekar.getTipPregleda().getMinimalnoTrajanjeMin() * 60 * 1000;

            if(pocetakStari <= pocetakNovi && krajStari >= pocetakNovi) {
                return false;
            } else if(pocetakStari >= pocetakNovi && krajStari <= krajNovi) {
                return false;
            } else if(pocetakNovi <= pocetakStari && krajNovi >= pocetakStari) {
                return false;
            } else if(pocetakStari <= pocetakNovi && krajStari >= krajNovi) {
                return false;
            }
        }
        Pregled pregled = new Pregled();

        pregled.setLekar(lekar);
        pregled.setPopust(0);
        pregled.setPacijent(pacijent);
        pregled.setDatumPocetka(date);
        pregled.setIzvestaj("");
        pregled.setTipPregleda(lekar.getTipPregleda());
        pregled.setDatumZavrsetka(new Date(date.getTime() + lekar.getTipPregleda().getMinimalnoTrajanjeMin() * 60 * 1000));

        pregledRepository.save(pregled);

        return true;
    }

    public Boolean otkaziPregled(Long kId, String pregledId) {
        Pacijent pacijent = pacijentRepository.findPacijentByKorisnikId(kId);
        Pregled pregled = pregledRepository.getOne(Long.parseLong(pregledId));

        Date sad = new Date();

        if(pregled.getPacijent().getId() != pacijent.getId() || pregled.getDatumPocetka().getTime() - 24 * 60 * 60 * 1000 < sad.getTime()) {
            return false;
        }

        pregledRepository.delete(pregled);

        return true;
    }

    @Transactional(readOnly = false)
    public Boolean zakaziPredefinisaniPregled(Long kId, String pregledId, String version) {
        Pacijent pacijent = pacijentRepository.findPacijentByKorisnikId(kId);
        Pregled pregled = pregledRepository.getOne(Long.parseLong(pregledId));
        if(pregled.getVersion()!= Long.parseLong(version)){
            return false;
        }

        for (Pregled p: pacijent.getPregledi()) {

            long pocetakStari = p.getDatumPocetka().getTime();
            long krajStari = p.getDatumZavrsetka().getTime();
            long pocetakNovi = pregled.getDatumPocetka().getTime();
            long krajNovi = pregled.getDatumZavrsetka().getTime();

            if(pocetakStari <= pocetakNovi && krajStari >= pocetakNovi) {
                return false;
            } else if(pocetakStari >= pocetakNovi && krajStari <= krajNovi) {
                return false;
            } else if(pocetakNovi <= pocetakStari && krajNovi >= pocetakStari) {
                return false;
            } else if(pocetakStari <= pocetakNovi && krajStari >= krajNovi) {
                return false;
            }
        }
        pregled.setPacijent(pacijent);
        pregledRepository.save(pregled);

        return true;
    }

    public List<Pregled> findPreglediZaSestru(String id, String date) throws ParseException {
        Date datum = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Date tomorrow =new SimpleDateFormat("yyyy-MM-dd").parse(date);
        tomorrow.setTime(datum.getTime() + (1000 * 60 * 60 * 24));
        return pregledRepository.findSestraPreglediDatum(Long.parseLong(id), datum, tomorrow);
    }

    public List<Pregled> findPreglediZaLekara(String id, String date) throws ParseException {
        Date datum = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Date tomorrow =new SimpleDateFormat("yyyy-MM-dd").parse(date);
        tomorrow.setTime(datum.getTime() + (1000 * 60 * 60 * 24));
        return pregledRepository.findLekarPreglediDatum(Long.parseLong(id), datum, tomorrow);
    }

    public List<Pregled> findPreglediZaSestruOdmor(String id, String date1, String date2) throws ParseException {
        Date datum = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
        Date kraj =new SimpleDateFormat("yyyy-MM-dd").parse(date2);
        kraj.setTime(kraj.getTime() + (1000 * 60 * 60 * 24));
        return pregledRepository.findSestraPreglediDatum(Long.parseLong(id), datum, kraj);
    }

    public List<Pregled> findPreglediZaLekaraOdmor(String id, String date1, String date2) throws ParseException {
        Date datum = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
        Date kraj =new SimpleDateFormat("yyyy-MM-dd").parse(date2);
        kraj.setTime(kraj.getTime() + (1000 * 60 * 60 * 24));
        return pregledRepository.findLekarPreglediDatum(Long.parseLong(id), datum, kraj);
    }

    public List<Pregled> getZavrseniPreglediByLekarId(Long id) {

        List<Pregled> pregledi = pregledRepository.findPregledsByLekarIdAndStanje(id, 2);

        return pregledi;
    }

    public List<Pregled> getPreglediKojiNemajuSalu(String id) {

        List<Pregled> pregledi = pregledRepository.preglediKojiNemajuSalu(Long.parseLong(id));

        return pregledi;
    }

    public Pregled sacuvajPregled(String pregledId, String salaId, String lekarId, String medSestraId, String termin) throws ParseException, MessagingException, InterruptedException {

        Date date;
        if(!String.valueOf(termin.charAt(4)).equals("-")){
            date =  new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(termin);
        }else{
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(termin);
        }
        Pregled pregled = pregledRepository.findById(Long.parseLong(pregledId)).get();
        Lekar lekar = lekarRepository.findLekarById(Long.parseLong(lekarId));
        Sala sala = salaRepository.findById(Long.parseLong(salaId)).get();
        MedicinskaSestra medicinskaSestra = medSestraRepository.findById(Long.parseLong(medSestraId)).get();

        if(!pregled.getDatumPocetka().equals(date)){
            pregled.setDatumPocetka(date);
            pregled.setDatumZavrsetka(new Date(date.getTime() + lekar.getTipPregleda().getMinimalnoTrajanjeMin() * 60 * 1000));
        }

        pregled.setLekar(lekar);
        pregled.setSala(sala);
        pregled.setMedicinskaSestra(medicinskaSestra);

        if(!lekar.getPregledi().contains(pregled)){
            lekar.getPregledi().add(pregled);
        }
        if(!sala.getPregled().contains(pregled)){
            sala.getPregled().add(pregled);
        }
        if(!medicinskaSestra.getPregledi().contains(pregled)){
            medicinskaSestra.getPregledi().add(pregled);
        }

        Klinika klinika = lekar.getKlinika();

        emailService.sendOsobljePregledRezervacijaSale(lekar.getKorisnik().getEmail(), date, sala.getSalaIdentifier());
        emailService.sendOsobljePregledRezervacijaSale(medicinskaSestra.getKorisnik().getEmail(), date, sala.getSalaIdentifier());
        emailService.sendPacijentPregledRezervacijaSale(pregled.getPacijent().getKorisnik().getEmail(), date, klinika.getNaziv(), klinika.getAdresa(), sala.getSalaIdentifier());

        lekarRepository.save(lekar);
        medSestraRepository.save(medicinskaSestra);
        salaRepository.save(sala);
        pregledRepository.save(pregled);
        return pregled;
    }

    public Boolean zakaziPregledByLekar(Long korisnikId, String lekarId, String datum) throws ParseException, MessagingException, InterruptedException {
        Lekar lekar = lekarRepository.findLekarById(Long.parseLong(lekarId));

        Date date = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(datum);

        //ovde bi mozda trebalo staviti upit u bazu umesto for petlje
        for(int i = 0; i < lekar.getPregledi().size(); i++) {
            if(date.getTime() == lekar.getPregledi().get(i).getDatumPocetka().getTime()) {
                return false;
            }
        }

        //trebala bi da postoji provera i za operacije
        Pacijent pacijent = pacijentRepository.findPacijentByKorisnikId(korisnikId);
        List<Pregled> pregledi = pregledRepository.findPregledByDatumPac(pacijent.getId(), date);
        for (Pregled p: pregledi) {

            long pocetakStari = p.getDatumPocetka().getTime();
            long krajStari = p.getDatumZavrsetka().getTime();
            long pocetakNovi = date.getTime();
            long krajNovi = date.getTime() + lekar.getTipPregleda().getMinimalnoTrajanjeMin() * 60 * 1000;

            if(pocetakStari <= pocetakNovi && krajStari >= pocetakNovi) {
                return false;
            } else if(pocetakStari >= pocetakNovi && krajStari <= krajNovi) {
                return false;
            } else if(pocetakNovi <= pocetakStari && krajNovi >= pocetakStari) {
                return false;
            } else if(pocetakStari <= pocetakNovi && krajStari >= krajNovi) {
                return false;
            }
        }
        Pregled pregled = new Pregled();

        pregled.setLekar(lekar);
        pregled.setPopust(0);
        pregled.setPacijent(pacijent);
        pregled.setDatumPocetka(date);
        pregled.setIzvestaj("");
        pregled.setTipPregleda(lekar.getTipPregleda());
        pregled.setDatumZavrsetka(new Date(date.getTime() + lekar.getTipPregleda().getMinimalnoTrajanjeMin() * 60 * 1000));

        pregledRepository.save(pregled);
        List<AdminKlinike> admini = adminKlinikeRepository.getAdminKlinikesByKlinikaId(lekar.getKlinika().getId());
        for(AdminKlinike adminKlinike: admini){
            emailService.sendAdminuZakazivanjePregledaOperacije(adminKlinike.getKorisnik().getEmail(), "Lekar " + lekar.getKorisnik().getIme() + " " + lekar.getKorisnik().getPrezime()+" je poslao novi zahtev za nalazenje sale za pregled." );
        }
        return true;
    }
}
