package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Pregled;
import com.isapsw.Projekat.domain.TipPregleda;
import com.isapsw.Projekat.dto.TipPregledaDTO;
import com.isapsw.Projekat.repository.PacijentRepository;
import com.isapsw.Projekat.repository.PregledRepository;
import com.isapsw.Projekat.repository.TipPregledaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TipPregledaService {

    @Autowired
    private TipPregledaRepository tipPregledaRepository;

    private PregledRepository pregledRepository;

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

    public List<TipPregleda> getTipoviZaIzmenu(String id){
        Date date = Calendar.getInstance().getTime();
        return tipPregledaRepository.findIfNotReserved(Long.parseLong(id), date);
    }

    public TipPregleda editTip(TipPregledaDTO tipPregledaDTO){
        TipPregleda tp = tipPregledaRepository.findById(tipPregledaDTO.getId()).get();
        tp.setNaziv(tipPregledaDTO.getNaziv());
        tp.setMinimalnoTrajanjeMin(Integer.parseInt(tipPregledaDTO.getMinimalnoTrajanjeMin()));
        tp.setCenaPregleda(Integer.parseInt(tipPregledaDTO.getCenaPregleda()));
        tp.setCenaOperacije(Integer.parseInt(tipPregledaDTO.getCenaOperacije()));
        return tipPregledaRepository.save(tp);
    }
}
