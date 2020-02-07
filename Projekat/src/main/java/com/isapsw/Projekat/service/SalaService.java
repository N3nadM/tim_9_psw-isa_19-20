package com.isapsw.Projekat.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.SalaDTO;
import com.isapsw.Projekat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
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

    @Autowired
    private MedSestraService medSestraService;

    @Autowired
    private LekarRepository lekarRepository;

    @Autowired
    private LekarService lekarService;

    @Autowired
    private TipPregledaRepository tipPregledaRepository;

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
        Date datum;
        if(!String.valueOf(termin.charAt(4)).equals("-")){
            datum =  new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(termin);
        }else{
            datum = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(termin);
        }
        List<Pregled> pregledi = pregledRepository.findByPregledDatumPocetka(datum);
        for(Pregled p : pregledi){
            //brisanje sale iz liste za koju se vrsi provera
            saleNaKlinici.remove(p.getSala());
            if(saleNaKlinici.contains(p.getSala())){
                System.out.println("Nasao pregled koji se odrzava o sali");
                if(datum.getTime() > p.getDatumPocetka().getTime() && datum.getTime() <p.getDatumZavrsetka().getTime() ){
                    //proverava da li se vreme pocetka novog pregleda nalazi izmedju pocetka i zavrsetka vec rezervisanog pregleda u toj sali
                }else if((datum.getTime() + Integer.parseInt(trajanje)*60*1000) >p.getDatumPocetka().getTime() && (datum.getTime() + Integer.parseInt(trajanje) *60 * 1000) <p.getDatumZavrsetka().getTime()){
                    //proverava da li se vreme zavrsetka novog pregleda nalazi izmedju pocetka i zavrsetka vec rezervisanog pregleda u toj sali
                }else{
                    //ako nije nijedan od prethodnih uslova ispunjen ta sala je dostupna i ubacije se u listu
                    saleNaKlinici.add(p.getSala());
                }
            }
        }

        return  saleNaKlinici;
    }

    public HashMap<Long, String> prviSlobodniTerminiSala(String lekarId, String id, String termin, String trajanje) throws ParseException {
        HashMap<Long, String> ret = new HashMap<>();
        List<Sala> saleNaKlinici = salaRepository.findByKlinikaId(Long.parseLong(id));
        Lekar lekar = lekarRepository.findLekarByKorisnikId(Long.parseLong(lekarId));


        for(Sala s: saleNaKlinici){
            Date datum;
            Date zaRacunanjeKrajTermina;
            SimpleDateFormat formatter;
            Date dateTemp;
            Date pocetakRadnogVremena;
            Date krajRadnogVremena;
            boolean poklopiloSe = false;

            if(!String.valueOf(termin.charAt(4)).equals("-")){
                datum =  new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(termin);
                zaRacunanjeKrajTermina =  new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(termin);
                dateTemp =  new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(termin);
                pocetakRadnogVremena = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(termin.split(" ")[0] + " 07:00:00");
                krajRadnogVremena = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(termin.split(" ")[0] + " 22:00:00");
                formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            }else{
                datum = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(termin);
                zaRacunanjeKrajTermina = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(termin);
                dateTemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(termin);
                pocetakRadnogVremena = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(termin.split(" ")[0] + " 07:00:00");
                krajRadnogVremena = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(termin.split(" ")[0] + " 22:00:00");
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }

            datum.setTime(datum.getTime() + Integer.parseInt(trajanje)*60*1000);
            zaRacunanjeKrajTermina.setTime(zaRacunanjeKrajTermina.getTime() + Integer.parseInt(trajanje)*60*1000);

            while(!ret.containsKey(s.getId())){

                zaRacunanjeKrajTermina.setTime(datum.getTime() + Integer.parseInt(trajanje)*60*1000);

                dateTemp.setTime(datum.getTime() + 60*1000);
                List<Pregled> pregleds = pregledRepository.proveraSlobodniTerminSala(s.getId(), dateTemp, zaRacunanjeKrajTermina);
                List<Operacija> operacijas = operacijaRepository.proveraSlobodniTerminSala(s.getId(), dateTemp, zaRacunanjeKrajTermina);

                if(krajRadnogVremena.compareTo(zaRacunanjeKrajTermina) < 0){
                    //Ako je kraj radnog vremena manji od kraja termina,
                    // povecaj radno vreme za jedan dan i gledaj za sledeci dan
                    krajRadnogVremena.setTime(krajRadnogVremena.getTime() + 24*60*60*1000);
                    pocetakRadnogVremena.setTime(pocetakRadnogVremena.getTime() + 24*60*60*1000);
                    datum.setTime(pocetakRadnogVremena.getTime());
                    zaRacunanjeKrajTermina.setTime(pocetakRadnogVremena.getTime());
                    continue;
                }

                if((pregleds == null || pregleds.isEmpty()) && (operacijas == null || operacijas.isEmpty()) && (medSestraService.getDostupnaSestra(id, formatter.format(dateTemp), trajanje) != null)){
                    List<String> slobodniTerminiLekara;
                    String prepravljenDatum;
                    SimpleDateFormat format;

                    if(!String.valueOf(termin.charAt(4)).equals("-")){
                        format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                        prepravljenDatum =  format.format(datum);
                        prepravljenDatum = prepravljenDatum.replace(" ", "T") + ".000Z";
                    }else{
                        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        prepravljenDatum =  format.format(datum);
                        prepravljenDatum = prepravljenDatum.replace(" ", "T") + ".000Z";
                    }

                    slobodniTerminiLekara = lekarService.findSlobodniTermini(lekar.getId(), prepravljenDatum);

                    for(String pocetakTermina : slobodniTerminiLekara){
                        Date pocetak = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(pocetakTermina);
                        Date kraj = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(pocetakTermina);
                        kraj.setTime(kraj.getTime() + lekar.getTipPregleda().getMinimalnoTrajanjeMin()*60*1000);
                        System.out.println(pocetak);
                        System.out.println(datum);
                        if(pocetak.compareTo(datum) == 0){
                            poklopiloSe = true;
                            break;
                        }
                    }



                    String formattedDate = formatter.format(datum);
                    ret.put(s.getId(), formattedDate);
                    break;
                }else {
                    datum.setTime(datum.getTime() + Integer.parseInt(trajanje)*60*1000);
                }
            }
            if(poklopiloSe == false){
                continue;
            }
        }


        return ret;
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
