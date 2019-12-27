package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Klinika;
import com.isapsw.Projekat.domain.Pregled;
import com.isapsw.Projekat.domain.Sala;
import com.isapsw.Projekat.dto.SalaDTO;
import com.isapsw.Projekat.repository.KlinikaRepository;
import com.isapsw.Projekat.repository.OperacijaRepository;
import com.isapsw.Projekat.repository.PregledRepository;
import com.isapsw.Projekat.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private KlinikaRepository klinikaRepository;

    @Autowired
    private PregledRepository pregledRepository;

    @Autowired
    private OperacijaRepository operacijaRepository;

    public Sala addSala(SalaDTO salaDTO){
        Sala s = new Sala();
        s.setAktivna(true);
        s.setKlinika(klinikaRepository.findById(salaDTO.getKlinikaId()).get());
        s.setSalaIdentifier(salaDTO.getSalaIdentifier());
        s.setNaziv(salaDTO.getNaziv());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date d = new Date();
        dateFormat.format(d);
        s.setDatumKreiranja(d);
        return salaRepository.save(s);
    }

    public List<Sala> getSaleNaKlinici(String id){
        return salaRepository.findByKlinikaId(Long.parseLong(id));
    }

    public List<Sala> getDostupneSale(String id, String termin, String trajanje) throws ParseException {
        List<Sala> saleNaKlinici = salaRepository.findByKlinikaId(Long.parseLong(id));
        Date datum = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(termin);
        List<Pregled> pregledi = pregledRepository.findByPregledDatumPocetka(datum);
        for(Pregled p : pregledi){
            //brisanje sale iz liste za koju se vrsi provera
            saleNaKlinici.remove(p.getSala());
            if(saleNaKlinici.contains(p.getSala())){
                System.out.println("Nasao pregled koji se odrzava o sali");
                if(datum.getTime() > p.getDatumPocetka().getTime() && datum.getTime() <p.getDatumZavrsetka().getTime() ){
                    //proverava da li se vreme pocetka novog pregleda nalazi izmedju pocetka i zavrsetka vec rezervisanog pregleda u toj sali
                }else if((datum.getTime() + Integer.parseInt(trajanje)*60*100) >p.getDatumPocetka().getTime() && (datum.getTime() + Integer.parseInt(trajanje) *60 * 100) <p.getDatumZavrsetka().getTime()){
                    //proverava da li se vreme zavrsetka novog pregleda nalazi izmedju pocetka i zavrsetka vec rezervisanog pregleda u toj sali
                }else{
                    //ako nije nijedan od prethodnih uslova ispunjen ta sala je dostupna i ubacije se u listu
                    saleNaKlinici.add(p.getSala());
                }
            }
        }

        return  saleNaKlinici;
    }

    public List<Sala> search(Long id, String broj, String naziv){
        List<Sala> sale = salaRepository.findSalaByParameters(broj, naziv);
        List<Sala> ret = new ArrayList<>();
        for(Sala s: sale){
            if(s.getKlinika().getId().equals(id)){
                ret.add(s);
            }
        }
        return ret;
    }

    public List<Sala> getSaleKojeSeMoguObrisati(String id){
        List<Sala> salas = salaRepository.findByKlinikaId(Long.parseLong(id));
        Date date = Calendar.getInstance().getTime();
        //lista sala u kojima ima zakazanih pregleda u buducnosti
        List<Sala> salePregled = pregledRepository.findSaleUKojimaImaZakazanihPregleda(Long.parseLong(id), date);
        //lista sala u kojima ima zakazanih operacija u buducnosti
        List<Sala> saleOperacija = operacijaRepository.findSaleUKojimaImaZakazanihOperacija(Long.parseLong(id), date);
        List<Sala> ret = new ArrayList<>();
        for(Sala s: salas){
            if(!salePregled.contains(s) && !saleOperacija.contains(s)){
                ret.add(s);
            }
        }
        return ret;
    }

    public Sala obrisiSalu(String id){
        Sala sala = salaRepository.findById(Long.parseLong(id)).get();
        sala.setAktivna(false);
        return salaRepository.save(sala);
    }


    public Sala editSala(SalaDTO salaDTO){
        Sala sala = salaRepository.findById(salaDTO.getId()).get();
        sala.setNaziv(salaDTO.getNaziv());
        sala.setSalaIdentifier(salaDTO.getSalaIdentifier());
        return salaRepository.save(sala);
    }

}
