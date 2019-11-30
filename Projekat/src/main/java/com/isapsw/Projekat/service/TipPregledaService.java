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

    public List<TipPregleda> getAllTipoviPregleda() { return  tipPregledaRepository.findAll(); }

    public Optional<TipPregleda> getTipPregledaById(Long id) {return  tipPregledaRepository.findById(id);}
}
