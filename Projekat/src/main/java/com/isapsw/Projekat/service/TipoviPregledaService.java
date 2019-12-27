package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Klinika;
import com.isapsw.Projekat.domain.TipPregleda;
import com.isapsw.Projekat.dto.TipPregledaDTO;
import com.isapsw.Projekat.repository.KlinikaRepository;
import com.isapsw.Projekat.repository.TipoviPregledaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoviPregledaService {

    @Autowired
    private TipoviPregledaRepository tipoviPregledaRepository;

    @Autowired
    private KlinikaRepository klinikaRepository;

    public List<String> getAllTipoviPregleda() {
        return tipoviPregledaRepository.findAllTpString();
    }

    public TipPregleda addTipPregleda(TipPregledaDTO tipPregledaDTO){
        TipPregleda t = new TipPregleda();
        t.setAktivan(true);
        t.setNaziv(tipPregledaDTO.getNaziv());
        t.setCenaOperacije(Integer.parseInt(tipPregledaDTO.getCenaOperacije()));
        t.setCenaPregleda(Integer.parseInt(tipPregledaDTO.getCenaPregleda()));
        t.setMinimalnoTrajanjeMin(Integer.parseInt(tipPregledaDTO.getMinimalnoTrajanjeMin()));
        Klinika k = klinikaRepository.findKlinikaById(tipPregledaDTO.getKlinikaId());
        t.setKlinika(k);
        return tipoviPregledaRepository.save(t);
    }
}
