package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.TipPregleda;
import com.isapsw.Projekat.repository.TipPregledaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipPregledaService {

    @Autowired
    private TipPregledaRepository tipPregledaRepository;

    public List<TipPregleda> getTipoviSaKlinike(String id) { return  tipPregledaRepository.findTipPregledasByKlinikaId(Long.parseLong(id)); }

    public List<TipPregleda> getAllTipoviPregleda() { return  tipPregledaRepository.findAll(); }

    public Optional<TipPregleda> getTipPregledaById(Long id) {return  tipPregledaRepository.findById(id);}

    public List<TipPregleda> searchTipoviNaKlinici(Long id, String naziv, String cena,String trajanje){
        Integer najvecaCena = Integer.MAX_VALUE;
        if(!cena.equals("")){
            najvecaCena = Integer.parseInt(cena);
        }
        Integer minimalnoTrajanjeMin = 0;
        if(!trajanje.equals("")){
            minimalnoTrajanjeMin= Integer.parseInt(trajanje);
        }
        return tipPregledaRepository.findTipByParameters(id, naziv.toUpperCase(), najvecaCena, minimalnoTrajanjeMin);
    }
}
