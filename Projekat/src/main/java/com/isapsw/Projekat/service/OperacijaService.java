package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OperacijaService {
    @Autowired
    private OperacijaRepository operacijaRepository;

    @Autowired
    private PregledRepository pregledRepository;

    @Autowired
    private PacijentRepository pacijentRepository;

    @Autowired
    private LekarRepository lekarRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private MedSestraRepository medSestraRepository;

    @Autowired
   private EmailService emailService;

    @Autowired
    private AdminKlinikeRepository adminKlinikeRepository;

    public Operacija getOperacijaById(Long id){
        return operacijaRepository.findById(id).get();
    }

    public List<Operacija> getOperacijeByPacijentId(Long id) {
        return operacijaRepository.findOperacijeByPacijentIdAndSalaIsNotNull(id);
    }

    public List<Operacija> getOperacijeByLekarId(Long id) {
        return operacijaRepository.findOperacijasByLekari(id);
    }

    public List<Operacija> getZavrseneOperacijeByLekarIdAndStanje(Long id, int stanje) {
        return operacijaRepository.findOperacijasByLekariAndStanje(id, stanje);
    }

    public List<Operacija> getOperacijeByMedSestraId(Long id) {
        return operacijaRepository.findOperacijasByMedicinskaSestraId(id);
    }

    public List<Operacija> getOperacijeBySalaId(Long id) { return operacijaRepository.findOperacijasBySalaId(id); }

    public List<Operacija> findOperacijeZaSestru(String id, String date) throws ParseException {
        Date datum = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Date tomorrow =new SimpleDateFormat("yyyy-MM-dd").parse(date);
        tomorrow.setTime(datum.getTime() + (1000 * 60 * 60 * 24));
        return operacijaRepository.findSestraOperacijeDatum(Long.parseLong(id), datum, tomorrow);
    }

    public List<Operacija> findOperacijeZaLekara(String id, String date) throws ParseException {
        Date datum = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Date tomorrow =new SimpleDateFormat("yyyy-MM-dd").parse(date);
        tomorrow.setTime(datum.getTime() + (1000 * 60 * 60 * 24));
        List<Operacija> operacijas = operacijaRepository.findOperacijeDatum(datum, tomorrow);
        List<Operacija> ret = new ArrayList<>();
        Lekar l = lekarRepository.findLekarById(Long.parseLong(id));
        for(Operacija o : operacijas){
            if(o.getLekari().contains(l)){
                ret.add(o);
            }
        }
        return ret;
    }

    public List<Operacija> findOperacijeZaSestruOdmor(String id, String date1, String date2) throws ParseException {
        Date datum = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
        Date kraj =new SimpleDateFormat("yyyy-MM-dd").parse(date2);
        kraj.setTime(kraj.getTime() + (1000 * 60 * 60 * 24));
        return operacijaRepository.findSestraOperacijeDatum(Long.parseLong(id), datum, kraj);
    }

    public List<Operacija> findOperacijeZaLekaraOdmor(String id, String date1, String date2) throws ParseException {
        Date datum = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
        Date kraj =new SimpleDateFormat("yyyy-MM-dd").parse(date2);
        kraj.setTime(kraj.getTime() + (1000 * 60 * 60 * 24));
        List<Operacija> operacijas = operacijaRepository.findOperacijeDatum(datum, kraj);
        List<Operacija> ret = new ArrayList<>();
        Lekar l = lekarRepository.findLekarById(Long.parseLong(id));
        for(Operacija o : operacijas){
            if(o.getLekari().contains(l)){
                ret.add(o);
            }
        }
        return ret;
    }

    public Boolean zakaziOperaciju(Long korisnikId, String lekarId, String datum) throws ParseException {
        Lekar lekar = lekarRepository.findLekarById(Long.parseLong(lekarId));

        Date date = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(datum);

        //ovde bi mozda trebalo staviti upit u bazu umesto for petlje
        for(int i = 0; i < lekar.getPregledi().size(); i++) {
            if(date.getTime() == lekar.getPregledi().get(i).getDatumPocetka().getTime()) {
                return false;
            }
        }

        for(int i = 0; i < lekar.getOperacije().size(); i++) {
            if(date.getTime() == lekar.getOperacije().get(i).getDatumPocetka().getTime()) {
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
        Operacija operacija = new Operacija();

        if(operacija.getLekari() == null){
            operacija.setLekari(new ArrayList<>());
        }
        operacija.getLekari().add(lekar);
        operacija.setPacijent(pacijent);
        operacija.setDatumPocetka(date);
        operacija.setIzvestaj("");
        operacija.setTipPregleda(lekar.getTipPregleda());
        operacija.setDatumZavrsetka(new Date(date.getTime() + lekar.getTipPregleda().getMinimalnoTrajanjeMin() * 60 * 1000));
        operacija.setTipOperacije("ovo treba da se izbrise");
        operacijaRepository.save(operacija);

        return true;
    }

    public List<Operacija> operacijeKojeNemajuSalu(String id){
        return operacijaRepository.operacijeKojeNemajuSalu(Long.parseLong(id));
    }

    @Transactional
    public Operacija sacuvajOperaciju(String operacijaId, String salaId, List<Integer> lekariId, String medSestraId, String termin) throws ParseException, MessagingException, InterruptedException {

        Date date = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(termin);
        Operacija operacija = operacijaRepository.findByIdTransaction(Long.parseLong(operacijaId)).get();
        Sala sala = salaRepository.findByIdTransaction(Long.parseLong(salaId)).get();

        operacija.setSala(sala);
        if(operacija.getDatumPocetka() != date){
            operacija.setDatumPocetka(date);
            Lekar lekarTemp = lekarRepository.findByIdTransaction(lekariId.get(0).longValue()).get();
            operacija.setDatumZavrsetka(new Date(date.getTime() + lekarTemp.getTipPregleda().getMinimalnoTrajanjeMin() * 60 * 1000));
        }
        sala.getOperacija().add(operacija);

        MedicinskaSestra medicinskaSestra = medSestraRepository.findByIdTransaction(Long.parseLong(medSestraId)).get();

        if(!medicinskaSestra.getOperacije().contains(operacija)){
            medicinskaSestra.getOperacije().add(operacija);
        }
        if(operacija.getMedicinskaSestra() == null || !operacija.getMedicinskaSestra().equals(medicinskaSestra)){
            operacija.setMedicinskaSestra(medicinskaSestra);
        }

        for(Integer id : lekariId){
            Lekar lekar = lekarRepository.findByIdTransaction(id.longValue()).get();
            if(!operacija.getLekari().contains(lekar)){
                operacija.getLekari().add(lekar);
            }
            if(!lekar.getOperacije().contains(operacija)){
                lekar.getOperacije().add(operacija);
            }

            emailService.sendOsobljeOperacijaRezervacijaSale(lekar.getKorisnik().getEmail(), date, sala.getSalaIdentifier());
            lekarRepository.save(lekar);
        }
        Klinika klinika = medicinskaSestra.getKlinika();
        System.out.println(klinika.getNaziv());

        emailService.sendOsobljeOperacijaRezervacijaSale(medicinskaSestra.getKorisnik().getEmail(), date, sala.getSalaIdentifier());
        emailService.sendPacijentOperacijaRezervacijaSale(operacija.getPacijent().getKorisnik().getEmail(), date, klinika.getNaziv(), klinika.getAdresa(), sala.getSalaIdentifier());

        medSestraRepository.save(medicinskaSestra);
        salaRepository.save(sala);
        operacijaRepository.save(operacija);

        return operacija;
    }

    public Boolean zakaziOperacijuByLekar(Long korisnikId, String lekarId, String datum) throws ParseException, MessagingException, InterruptedException {
        Lekar lekar = lekarRepository.findLekarById(Long.parseLong(lekarId));

        Date date = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(datum);

        //ovde bi mozda trebalo staviti upit u bazu umesto for petlje
        for(int i = 0; i < lekar.getPregledi().size(); i++) {
            if(date.getTime() == lekar.getPregledi().get(i).getDatumPocetka().getTime()) {
                return false;
            }
        }

        for(int i = 0; i < lekar.getOperacije().size(); i++) {
            if(date.getTime() == lekar.getOperacije().get(i).getDatumPocetka().getTime()) {
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
        Operacija operacija = new Operacija();

        if(operacija.getLekari() == null){
            operacija.setLekari(new ArrayList<>());
        }
        operacija.getLekari().add(lekar);
        operacija.setPacijent(pacijent);
        operacija.setDatumPocetka(date);
        operacija.setIzvestaj("");
        operacija.setTipPregleda(lekar.getTipPregleda());
        operacija.setDatumZavrsetka(new Date(date.getTime() + lekar.getTipPregleda().getMinimalnoTrajanjeMin() * 60 * 1000));
        operacija.setTipOperacije("ovo treba da se izbrise");
        operacijaRepository.save(operacija);
        List<AdminKlinike> admini = adminKlinikeRepository.getAdminKlinikesByKlinikaId(lekar.getKlinika().getId());
        for(AdminKlinike adminKlinike: admini){
            emailService.sendAdminuZakazivanjePregledaOperacije(adminKlinike.getKorisnik().getEmail(), "Lekar " + lekar.getKorisnik().getIme() + " " + lekar.getKorisnik().getPrezime()+" je poslao novi zahtev za nalazenje sale za operaciju." );
        }

        return true;
    }
}
