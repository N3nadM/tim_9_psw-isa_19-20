package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.MedicinskaSestra;
import com.isapsw.Projekat.domain.Pregled;
import com.isapsw.Projekat.domain.Sala;
import com.isapsw.Projekat.repository.MedSestraRepository;
import com.isapsw.Projekat.repository.PregledRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class MedSestraService {

    @Autowired
    MedSestraRepository medSestraRepository;

    @Autowired
    PregledRepository pregledRepository;

    public MedicinskaSestra findMedSestra(String id){
        return  medSestraRepository.findMedicinskaSestraByKorisnikId(Long.parseLong(id));
    }

    public MedicinskaSestra updateSestra(MedicinskaSestra medicinskaSestra){
        return medSestraRepository.save(medicinskaSestra);
    }

    public List<MedicinskaSestra> getDostupneSestre(String id, String termin, String trajanje) throws ParseException {
        List<MedicinskaSestra> sestreNaKlinici = medSestraRepository.findMedicinskaSestrasByKlinikaId(Long.parseLong(id));
        Date datum = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(termin);
        List<Pregled> pregledi = pregledRepository.findByPregledDatumPocetka(datum);
        for(Pregled p : pregledi){
            if(sestreNaKlinici.contains(p.getMedicinskaSestra())){
                //brisanje sestre iz liste za koju se vrsi provera
                sestreNaKlinici.remove(p.getMedicinskaSestra());
                System.out.println(LocalTime.parse(String.valueOf(datum.getTime())));
                System.out.println("Nasao sestru koja ima preglede taj dan");
                if(datum.getTime() > p.getDatumPocetka().getTime() && datum.getTime() <p.getDatumZavrsetka().getTime() ){
                    //proverava da li se vreme pocetka novog pregleda nalazi izmedju pocetka i zavrsetka pregleda koji sestra vec ima zakazano taj dan
                }else if((datum.getTime() + Integer.parseInt(trajanje)*60*100) >p.getDatumPocetka().getTime() && (datum.getTime() + Integer.parseInt(trajanje) *60 * 100) <p.getDatumZavrsetka().getTime()){
                    //proverava da li se vreme zavrsetka novog pregleda nalazi izmedju pocetka i zavrsetka pregleda koji sestra ima zakazano za taj dan
                }
                else{
                    //ako nije nijedan od prethodnih uslova ispunjen ta med sestra je dostupna i ubacije se u listu
                    sestreNaKlinici.add(p.getMedicinskaSestra());
                }
            }
        }

        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        String pocetak = df.format(datum.getTime());

        datum.setTime(datum.getTime() + Integer.parseInt(trajanje)*60*100);
        String kraj = df.format(datum.getTime());
        List<MedicinskaSestra> copy = new ArrayList<>(sestreNaKlinici);
        for(MedicinskaSestra ms: copy){
            System.out.println("Uslo");
            if(LocalTime.parse(pocetak).isAfter(ms.getPocetakRadnogVremena()) && LocalTime.parse(kraj).isBefore(ms.getKrajRadnogVremena())){
                //proverava da li se pocetak i kraj  pregleda nalaze u okviru radnog vremena medicinske sestre
                System.out.println("Nalazi se u radnom vremeneu");
            }else {
                sestreNaKlinici.remove(ms);
            }

        }

        return  sestreNaKlinici;
    }
}
