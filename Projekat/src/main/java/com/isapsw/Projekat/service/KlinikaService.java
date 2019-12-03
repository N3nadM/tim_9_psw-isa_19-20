package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.KlinikaDTO;
import com.isapsw.Projekat.repository.KlinikaRepository;
import com.isapsw.Projekat.repository.PregledRepository;
import com.isapsw.Projekat.repository.TipoviPregledaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class KlinikaService {

    @Autowired
    private KlinikaRepository klinikaRepository;

    @Autowired
    private PregledRepository pregledRepository;

    @Autowired
    private TipoviPregledaRepository tipoviPregledaRepository;

    public List<Klinika> getAllKlinike() {
        return klinikaRepository.findAll();
    }

    public Klinika addKlinika(Klinika klinika) {
        return klinikaRepository.save(klinika);
    }


    public List<Klinika> searchKlinike(String lokacija, String ocena, String tip, String datum) throws Exception {
        if(!datum.isEmpty() && tip.isEmpty()) {
            throw new Exception("Datum ne moze biti unet ako nije unet tip pregleda");
        }
        if(ocena.isEmpty()) {
            ocena = "0.0";
        }
        if(datum.isEmpty()) {
            return klinikaRepository.findKlinikaByParameters(lokacija.toUpperCase(), tip, Double.parseDouble(ocena));
        } else {
            List<Klinika> klinike = klinikaRepository.findKlinikaByParameters(lokacija.toUpperCase(), tip, Double.parseDouble(ocena));
            List<Pregled> pregledi = pregledRepository.findByPregledDatumPocetka(Date.from(Instant.parse(datum)));
            TipPregleda tipTrazenogPregleda = tipoviPregledaRepository.findFirstTipOnePregledaByNaziv(tip);

            Set<Klinika> retKlinike = new HashSet<>();

            //Provera lekara koji imaju zakazan pregled za taj dan, da li imaju u rasporedu slobodno
            for(int i = 0; i < pregledi.size(); i++ ) {
                Pregled pregled = pregledi.get(i);
                Lekar lekar = pregled.getLekar();
                lekar.getPregledi().sort((p, k) -> p.getDatumPocetka().after(k.getDatumPocetka()) ? 1 : -1);

                if(lekar.getTipPregleda().getNaziv().equals(tip)) {

                    Date pocetak = makeDateFromDateAndTime(pregled.getDatumPocetka(),lekar.getPocetakRadnogVremena());
                    Date kraj = makeDateFromDateAndTime(pregled.getDatumPocetka(),lekar.getKrajRadnogVremena());

                    List<Pregled> preglediIstogDanaJednogLekara = new ArrayList<>();
                    for (int j = 0; j < lekar.getPregledi().size(); j++) {
                        if (compareDatesOnly(pregled.getDatumPocetka(),lekar.getPregledi().get(j).getDatumPocetka())) { //Ako se poklapa lekarov pregled sa pregledom tog datuma proveri ima li slobodno u rasporedu za taj dan
                            System.out.println(pregled.getDatumPocetka().toString() + " , " + lekar.getPregledi().get(j).getDatumPocetka());
                            preglediIstogDanaJednogLekara.add(lekar.getPregledi().get(j));
                        }
                    }
                    for (int j = 0; j < preglediIstogDanaJednogLekara.size(); j++) {
                        if (pocetak.getTime() + tipTrazenogPregleda.getMinimalnoTrajanjeMin() * 60 * 1000 < preglediIstogDanaJednogLekara.get(j).getDatumPocetka().getTime() && pocetak.getTime() < kraj.getTime()) {
                            retKlinike.add(lekar.getKlinika());
                        } else {
                            pocetak.setTime(preglediIstogDanaJednogLekara.get(j).getDatumZavrsetka().getTime());

                            //Ako je dosao do poslednjeg pregleda tog dana proveri da li ima prostora od tad do kraja radnog vremena
                            if(j == preglediIstogDanaJednogLekara.size() - 1 && pocetak.getTime() + tipTrazenogPregleda.getMinimalnoTrajanjeMin() * 60 * 1000 < kraj.getTime()) {
                                retKlinike.add(lekar.getKlinika());
                            }
                        }
                    }
                }
            }

//             Provera ako lekar nema zakazn pregled za taj dan dodaj tu kliniku, ako je imao proveren je u petlji gore
            for(int i = 0; i < klinike.size(); i++) {
                List<Lekar> lekars =  klinike.get(i).getLekari();
                for(int j = 0; j < lekars.size(); j++) {

                    List<Pregled> pregleds = lekars.get(j).getPregledi();
                    boolean izbaci = false;

                    if(lekars.get(j).getTipPregleda().getNaziv().equals(tip) || tip.isEmpty()) {
                        for (int z = 0; z < pregleds.size(); z++) {
                            if (compareDatesOnly(pregleds.get(z).getDatumPocetka(), Date.from(Instant.parse(datum)))) {
                                izbaci = true;
                            }
                        }
                        if (!izbaci) {
                            retKlinike.add(lekars.get(j).getKlinika());
                        }
                    }
                }
            }

            return new ArrayList<>(retKlinike);
        }
    }

    private Date makeDateFromDateAndTime(Date d, LocalTime lt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, lt.getHour());
        cal.set(Calendar.MINUTE, lt.getMinute());
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    private boolean compareDatesOnly(Date d1, Date d2) {
        return d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().compareTo(d2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) == 0;
    }

    public Optional<Klinika> findKlinikaId(Long id){
        return klinikaRepository.findById(id);
    }

    public Klinika editKlinika(KlinikaDTO klinika){
        Klinika k = klinikaRepository.findById(klinika.getId()).get();
        if(k == null){
            return  k;
        }
        k.setAdresa(klinika.getAdresa());
        k.setNaziv(klinika.getNaziv());
        k.setOpis(klinika.getOpis());
        klinikaRepository.save(k);
        return k;
    }

    public Klinika getKlinikaById(Long id) {
        return klinikaRepository.findKlinikaById(id);
    }

    public List<Lekar> getLekariKlinike(Long id, String tip, String datum) throws Exception {
        if(!datum.isEmpty() && tip.isEmpty()) {
            throw new Exception("Datum ne moze biti unet ako nije unet tip pregleda");
        }

        Klinika klinika = klinikaRepository.findKlinikaById(id);
        TipPregleda tipTrazenogPregleda = tipoviPregledaRepository.findFirstTipOnePregledaByNaziv(tip);
        Set<Lekar> lekars = new HashSet<>();

        System.out.println("datum je " + datum);
        if(!datum.isEmpty()) {
            klinika.getLekari().forEach(lekar -> {
                if(lekar.getTipPregleda().getNaziv().equals(tip)) {
                    lekar.getPregledi().sort((p, k) -> p.getDatumPocetka().after(k.getDatumPocetka()) ? 1 : -1);
                    List<Pregled> preglediIstogDanaJednogLekara = new ArrayList<>();
                    Date pocetak = makeDateFromDateAndTime(Date.from(Instant.parse(datum)),lekar.getPocetakRadnogVremena());
                    Date kraj = makeDateFromDateAndTime(Date.from(Instant.parse(datum)),lekar.getKrajRadnogVremena());

                    lekar.getPregledi().forEach(pregled -> {
                        if (compareDatesOnly(Date.from(Instant.parse(datum)), pregled.getDatumPocetka())) {
                           preglediIstogDanaJednogLekara.add(pregled);
                        }
                    });
                    //Ako nema zakazanih pregleda za taj dan dodaj lekara u listu
                    if(preglediIstogDanaJednogLekara.size() == 0) {
                        lekars.add(lekar);
                    }
                    for (int j = 0; j < preglediIstogDanaJednogLekara.size(); j++) {
                        if (pocetak.getTime() + tipTrazenogPregleda.getMinimalnoTrajanjeMin() * 60 * 1000 < preglediIstogDanaJednogLekara.get(j).getDatumPocetka().getTime() && pocetak.getTime() < kraj.getTime()) {
                            lekars.add(lekar);
                        } else {
                            pocetak.setTime(preglediIstogDanaJednogLekara.get(j).getDatumZavrsetka().getTime());
                            //Ako je dosao do poslednjeg pregleda tog dana proveri da li ima prostora od tad do kraja radnog vremena
                            if(j == preglediIstogDanaJednogLekara.size() - 1 && pocetak.getTime() + tipTrazenogPregleda.getMinimalnoTrajanjeMin() * 60 * 1000 < kraj.getTime()) {
                                lekars.add(lekar);
                            }
                        }
                    }
                }
            });
        } else if (!tip.isEmpty()) {
            for (Lekar lekar : klinika.getLekari()) {
                if (lekar.getTipPregleda().getNaziv().equals(tip)) {
                    lekars.add(lekar);
                }
            }
        }
        else {
            return klinika.getLekari();
        }

        return new ArrayList<>(lekars);
    }
}
