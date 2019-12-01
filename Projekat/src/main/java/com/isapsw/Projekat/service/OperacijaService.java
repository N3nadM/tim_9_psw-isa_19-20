package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Operacija;
import com.isapsw.Projekat.repository.OperacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperacijaService {
    @Autowired
    private OperacijaRepository operacijaRepository;

    public List<Operacija> getOperacijeByPacijentId(Long id) {
        return operacijaRepository.findOperacijeByPacijentId(id);
    }
}
