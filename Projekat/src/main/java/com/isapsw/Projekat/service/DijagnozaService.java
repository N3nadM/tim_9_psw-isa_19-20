package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Dijagnoza;
import com.isapsw.Projekat.repository.DijagnozaRepository;
import com.isapsw.Projekat.repository.LekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DijagnozaService {

    @Autowired
    private LekRepository lekRepository;

    @Autowired
    private DijagnozaRepository dijagnozaRepository;

    public Dijagnoza addDijagnoza(Dijagnoza dijagnoza){ return dijagnozaRepository.save(dijagnoza); }

}
