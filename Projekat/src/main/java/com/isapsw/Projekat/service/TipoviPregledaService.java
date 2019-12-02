package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.TipPregleda;
import com.isapsw.Projekat.repository.TipoviPregledaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoviPregledaService {

    @Autowired
    private TipoviPregledaRepository tipoviPregledaRepository;

    public List<String> getAllTipoviPregleda() {
        return tipoviPregledaRepository.findAllTpString();
    }
}
