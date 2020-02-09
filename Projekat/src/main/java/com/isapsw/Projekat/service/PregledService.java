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
    private KlinikaRepository klinikaRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AdminKlinikeRepository adminKlinikeRepository;

    @Autowired
    private MedSestraService medSestraService;

    @Autowired
    private LekarService lekarService;

    @Autowired
    private SalaService salaService;

    public Pregled getPregledById(Long id){
        return pregledRepository.getOne(id);
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

    @Transactional
    public void AutomatskoBiranjePregledi() throws ParseException, MessagingException, InterruptedException {
        List<Klinika> sveKlinike = klinikaRepository.findAll();

        for(Klinika klinika : sveKlinike){
            List<Pregled> zahteviZaPreglede = pregledRepository.preglediKojiNemajuSalu(klinika.getId());

            for(Pregled pregled: zahteviZaPreglede){
                Integer trajanje = tipoviPregledaRepository.getMinimalnoTrajanje(pregled.getTipPregleda().getId());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d = simpleDateFormat.parse(pregled.getDatumPocetka().toString());
               if(medSestraService.getDostupnaSestra(klinika.getId().toString(),simpleDateFormat.format(d), trajanje.toString()) != null && !salaService.getDostupneSale(klinika.getId().toString(), simpleDateFormat.format(d), pregled.getTipPregleda().getMinimalnoTrajanjeMin().toString()).isEmpty()){
                   MedicinskaSestra ms = medSestraService.getDostupnaSestra(klinika.getId().toString(),simpleDateFormat.format(d), trajanje.toString());
                   Sala sala = salaService.getDostupneSale(klinika.getId().toString(), simpleDateFormat.format(d), trajanje.toString()).get(0);
                   sacuvajPregled(pregled.getId().toString(), sala.getId().toString(), pregled.getLekar().getId().toString(),ms.getId().toString(), simpleDateFormat.format(d) );
               }else {
                   Long lekarId = lekarRepository.getKorisnikId(pregled.getLekar().getId());
                   HashMap<Long, String> dostupneSaleSaTerminima = salaService.prviSlobodniTerminiSala(lekarId.toString(), klinika.getId().toString(), simpleDateFormat.format(d), trajanje.toString());
                   if(dostupneSaleSaTerminima.get(Long.valueOf(dostupneSaleSaTerminima.size())) != "nema"){
                       for(Long key : dostupneSaleSaTerminima.keySet()){
                           Sala s = salaRepository.findById(key).get();
                           MedicinskaSestra ms = medSestraService.getDostupnaSestra(klinika.getId().toString(), dostupneSaleSaTerminima.get(key), trajanje.toString());
                           sacuvajPregled(pregled.getId().toString(), s.getId().toString(), pregled.getLekar().getId().toString(),ms.getId().toString(), dostupneSaleSaTerminima.get(key) );
                           break;
                       }

                   }else{
                       while(true){
                           SimpleDateFormat format;
                           String terminPregleda;
                           d.setTime(d.getTime() + 24*60*60*1000);
                           if(!String.valueOf(simpleDateFormat.format(d).charAt(4)).equals("-")){
                               format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                               terminPregleda =  format.format(d);
                               terminPregleda = terminPregleda.replace(" ", "T") + ".000Z";
                           }else{
                               format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                               terminPregleda =  format.format(d);
                               terminPregleda = terminPregleda.replace(" ", "T") + ".000Z";
                           }

                           List<String> slobodniTerminiLekara = lekarService.findSlobodniTerminiClone(pregled.getLekar().getId(), terminPregleda);
                           if(!slobodniTerminiLekara.isEmpty()){
                               if(medSestraService.getDostupnaSestra(klinika.getId().toString(),slobodniTerminiLekara.get(0), trajanje.toString()) != null && !salaService.getDostupneSale(klinika.getId().toString(),slobodniTerminiLekara.get(0) , trajanje.toString()).isEmpty()){
                                   MedicinskaSestra ms = medSestraService.getDostupnaSestra(klinika.getId().toString(),slobodniTerminiLekara.get(0), trajanje.toString());
                                   Sala sala = salaService.getDostupneSale(klinika.getId().toString(),slobodniTerminiLekara.get(0), trajanje.toString()).get(0);
                                   sacuvajPregled(pregled.getId().toString(), sala.getId().toString(), pregled.getLekar().getId().toString(),ms.getId().toString(), slobodniTerminiLekara.get(0) );
                                   break;
                               }
                           }
                       }


                   }


               }
            }
        }
    }

    public Pregled addPregled(PregledDTO pregledDTO) throws ParseException {
        System.out.println(pregledDTO.getMedSestraId());
        Pregled pregled = new Pregled();
        Sala sala = salaRepository.getOne(Long.parseLong(pregledDTO.getSalaId()));
        pregled.setSala(sala);
        Lekar lekar = lekarRepository.findLekarById(Long.parseLong(pregledDTO.getLekarId()));
        pregled.setLekar(lekar);

        TipPregleda tp = tipoviPregledaRepository.getOne(Long.parseLong(pregledDTO.getTipPregledaId()));
        pregled.setTipPregleda(tp);

        pregled.setMedicinskaSestra(medSestraRepository.getOne(Long.parseLong(pregledDTO.getMedSestraId())));
        pregled.setPacijent(null);

        Date datum = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(pregledDTO.getDatum());
        Date d2 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(pregledDTO.getDatum());
        pregled.setDatumPocetka(datum);

        d2.setTime(d2.getTime() + tp.getMinimalnoTrajanjeMin() * 60 * 1000);
        pregled.setDatumZavrsetka(d2);

        Date date = Calendar.getInstance().getTime();
        pregled.setDatumKreiranja(date);
        pregled.setPopust(Integer.parseInt(pregledDTO.getPopust()));

        pregled.setIzvestaj("");
        return pregledRepository.save(pregled);
    }

    @Transactional
    public Boolean zakaziPregled(Long korisnikId, String lekarId, String datum) throws ParseException, MessagingException, InterruptedException {
        System.out.println(korisnikId);

        Lekar lekar = lekarRepository.findByIdTransaction(Long.parseLong(lekarId)).get();

        for(int i = 0; i < lekar.getKlinika().getAdminiKlinike().size(); i++) {
            emailService.sendAdminuZakazivanjePregledaOperacijePacijent(lekar.getKlinika().getAdminiKlinike().get(i).getKorisnik().getEmail(), "Upit za zakazivanje pregleda" + datum + ".");
        }

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

    @Transactional
    public Pregled sacuvajPregled(String pregledId, String salaId, String lekarId, String medSestraId, String termin) throws ParseException, MessagingException, InterruptedException {

        Date date;
        if(!String.valueOf(termin.charAt(4)).equals("-")){
            date =  new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(termin);
        }else{
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(termin);
        }
        Pregled pregled = pregledRepository.getOne(Long.parseLong(pregledId));
        Lekar lekar = lekarRepository.findByIdTransaction(Long.parseLong(lekarId)).get();
        Sala sala = salaRepository.findByIdTransaction(Long.parseLong(salaId)).get();
        MedicinskaSestra medicinskaSestra = medSestraRepository.findById(Long.parseLong(medSestraId)).get();

        if(!pregled.getDatumPocetka().equals(date)){
            pregled.setDatumPocetka(date);
            pregled.setDatumZavrsetka(new Date(date.getTime() + tipoviPregledaRepository.getMinimalnoTrajanje(lekar.getTipPregleda().getId()) * 60 * 1000));
        }

        pregled.setLekar(lekar);
        pregled.setSala(sala);
        pregled.setMedicinskaSestra(medicinskaSestra);

        List<Pregled> pregleds = lekarRepository.getPregledi(lekar.getId());
        if(!pregleds.contains(pregled)){
            pregleds.add(pregled);
            lekar.setPregledi(pregleds);
        }

        List<Pregled> pregleds1 = salaRepository.getPregledi(sala.getId());
        if(!pregleds1.contains(pregled)){
            pregleds1.add(pregled);
            sala.setPregled(pregleds1);
        }
        List<Pregled> pregleds2 = medSestraRepository.getPregledi(medicinskaSestra.getId());
        if(!pregleds2.contains(pregled)){
            pregleds2.add(pregled);
            medicinskaSestra.setPregledi(pregleds2);
        }

        Klinika klinika = lekarRepository.getKlinika(lekar.getId());

        emailService.sendOsobljePregledRezervacijaSale(lekar.getKorisnik().getEmail(), date, sala.getSalaIdentifier());
        emailService.sendOsobljePregledRezervacijaSale(medicinskaSestra.getKorisnik().getEmail(), date, sala.getSalaIdentifier());
        emailService.sendPacijentPregledRezervacijaSale(pregledRepository.getPacijent(pregled.getId()).getKorisnik().getEmail(), date, klinika.getNaziv(), klinika.getAdresa(), sala.getSalaIdentifier());

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
