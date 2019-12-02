package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.KlinikaDTO;
import com.isapsw.Projekat.repository.KlinikaRepository;
import com.isapsw.Projekat.repository.PregledRepository;
import com.isapsw.Projekat.repository.TipoviPregledaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

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


    public List<Klinika> searchKlinike(String lokacija, String ocena, String tip, String datum) {
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

                Date pocetak = makeDateFromDateAndTime(pregled.getDatumPocetka(),lekar.getPocetakRadnogVremena());
                Date kraj = makeDateFromDateAndTime(pregled.getDatumPocetka(),lekar.getKrajRadnogVremena());

                if(lekar.getTipPregleda().getNaziv().equals(tip)) {
                    for (int j = 0; j < lekar.getPregledi().size(); j++) {
                        if (compareDatesOnly(pregled.getDatumPocetka(),lekar.getPregledi().get(j).getDatumPocetka())) { //Ako se poklapa lekarov pregled sa pregledom tog datuma proveri ima li slobodno u rasporedu za taj dan
                            System.out.println(pocetak.toString() + "poredim sa " + lekar.getPregledi().get(j).getDatumPocetka().toString());
                            if (pocetak.getTime() + tipTrazenogPregleda.getMinimalnoTrajanjeMin() * 60 * 1000 < lekar.getPregledi().get(j).getDatumPocetka().getTime() && pocetak.getTime() < kraj.getTime()) {
                                System.out.println("dodao sam");
                                retKlinike.add(lekar.getKlinika());
                            } else {
                                pocetak = lekar.getPregledi().get(j).getDatumZavrsetka();
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
}
