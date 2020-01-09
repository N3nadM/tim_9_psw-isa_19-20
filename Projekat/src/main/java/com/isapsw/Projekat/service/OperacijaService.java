package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Lekar;
import com.isapsw.Projekat.domain.Operacija;
import com.isapsw.Projekat.domain.Pacijent;
import com.isapsw.Projekat.domain.Pregled;
import com.isapsw.Projekat.repository.LekarRepository;
import com.isapsw.Projekat.repository.OperacijaRepository;
import com.isapsw.Projekat.repository.PregledRepository;
import com.isapsw.Projekat.repository.PacijentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public List<Operacija> getOperacijeByPacijentId(Long id) {
        return operacijaRepository.findOperacijeByPacijentId(id);
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

}
