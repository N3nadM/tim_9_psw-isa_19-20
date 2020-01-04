package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Lekar;
import com.isapsw.Projekat.domain.Operacija;
import com.isapsw.Projekat.domain.Pregled;
import com.isapsw.Projekat.repository.LekarRepository;
import com.isapsw.Projekat.repository.OperacijaRepository;
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


}
