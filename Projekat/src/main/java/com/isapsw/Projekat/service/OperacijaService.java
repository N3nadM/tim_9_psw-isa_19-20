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

    public List<Operacija> getOperacijeByLekarId(Long id) {
        return operacijaRepository.findOperacijasByLekari(id);
    }

    public List<Operacija> getOperacijeByMedSestraId(Long id) {
        return operacijaRepository.findOperacijasByMedicinskaSestraId(id);
    }

    public List<Operacija> getOperacijeBySalaId(Long id) { return operacijaRepository.findOperacijasBySalaId(id); }

}
