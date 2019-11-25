package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Klinika;
import com.isapsw.Projekat.repository.KlinikaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KlinikaService {

    @Autowired
    private KlinikaRepository klinikaRepository;

    public List<Klinika> getAllKlinike() {
        return klinikaRepository.findAll();
    }

    public Klinika addKlinika(Klinika klinika) {
        return klinikaRepository.save(klinika);
    }

    public Optional<Klinika> findKlinikaId(Long id){
        return klinikaRepository.findById(id);
    }
}
