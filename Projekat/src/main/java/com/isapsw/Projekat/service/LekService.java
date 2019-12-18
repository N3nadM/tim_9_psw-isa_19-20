package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Lek;
import com.isapsw.Projekat.repository.LekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LekService {

    @Autowired
    private LekRepository lekRepository;

    public List<Lek> getAllLekovi(){ return lekRepository.findAll();}

    public Lek getLekByNaziv(String naziv){return lekRepository.findLekByNaziv(naziv);}

    public Lek addLek(Lek lek){ return lekRepository.save(lek);}
}
