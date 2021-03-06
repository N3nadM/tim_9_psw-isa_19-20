package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Pregled;
import com.isapsw.Projekat.domain.TipPregleda;
import com.isapsw.Projekat.dto.TipPregledaDTO;
import com.isapsw.Projekat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TipPregledaService {

    @Autowired
    private TipPregledaRepository tipPregledaRepository;

    @Autowired
    private PregledRepository pregledRepository;

    @Autowired
    private OperacijaRepository operacijaRepository;

    @Autowired
    private LekarRepository lekarRepository;

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
        //svi tipovi na klinici
        List<TipPregleda> sviTipoviNaKlinici = tipPregledaRepository.findTipPregledasByKlinikaId(Long.parseLong(id));
        //tipovi koji imaju preglede u buducnosti
        List<TipPregleda> tipovi = pregledRepository.findTipoveKojiImajuZakazanePreglede(Long.parseLong(id), date);
        //tipovi koji imaju operacije u buducnosti
        List<TipPregleda> tipoviOp = operacijaRepository.findTipoveKojiImajuZakazaneOperacije(Long.parseLong(id), date);

        List<TipPregleda> tipoviLekari = lekarRepository.getTipoviSvihLekara(Long.parseLong(id));
        List<TipPregleda> ret = new ArrayList<>();
        for(TipPregleda t : sviTipoviNaKlinici){
            if(!tipovi.contains(t) && !tipoviOp.contains(t) && !tipoviLekari.contains(t)){
                ret.add(t);
            }
        }
        return ret;
    }

    public TipPregleda editTip(TipPregledaDTO tipPregledaDTO){
        TipPregleda tp = tipPregledaRepository.findById(tipPregledaDTO.getId()).get();
        tp.setNaziv(tipPregledaDTO.getNaziv());
        tp.setMinimalnoTrajanjeMin(Integer.parseInt(tipPregledaDTO.getMinimalnoTrajanjeMin()));
        tp.setCenaPregleda(Integer.parseInt(tipPregledaDTO.getCenaPregleda()));
        tp.setCenaOperacije(Integer.parseInt(tipPregledaDTO.getCenaOperacije()));
        return tipPregledaRepository.save(tp);
    }

    public TipPregleda deleteTip(String id){
        TipPregleda tp = tipPregledaRepository.findById(Long.parseLong(id)).get();
        tp.setAktivan(false);
        return tipPregledaRepository.save(tp);
    }
}
