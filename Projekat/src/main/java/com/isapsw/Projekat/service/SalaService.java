package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Klinika;
import com.isapsw.Projekat.domain.Sala;
import com.isapsw.Projekat.dto.SalaDTO;
import com.isapsw.Projekat.repository.KlinikaRepository;
import com.isapsw.Projekat.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private KlinikaRepository klinikaRepository;

    public Sala addSala(SalaDTO salaDTO){
        Sala s = new Sala();
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

}
