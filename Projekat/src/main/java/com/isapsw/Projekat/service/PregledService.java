package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.PregledDTO;
import com.isapsw.Projekat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        for(int i = 0; i < lekar.getPregledi().size(); i++) {
            if(date.getTime() == lekar.getPregledi().get(i).getDatumPocetka().getTime()) {
                return false;
            }
        }

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

    @Transactional
    public Boolean zakaziPredefinisaniPregled(Long kId, String pregledId) {
        Pacijent pacijent = pacijentRepository.findPacijentByKorisnikId(kId);
        Pregled pregled = pregledRepository.getOne(Long.parseLong(pregledId));

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
}
