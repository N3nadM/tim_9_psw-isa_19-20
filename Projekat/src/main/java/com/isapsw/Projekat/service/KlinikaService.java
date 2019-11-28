package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Klinika;
import com.isapsw.Projekat.repository.KlinikaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Klinika> searchKlinike(String lokacija, String ocena) {
        if(ocena.isEmpty()) {
            return klinikaRepository.findKlinikaByAdresaContaining(lokacija.toUpperCase());
        } else {
            return klinikaRepository.findKlinikaByAdresaAndOcena(lokacija.toUpperCase(), Double.parseDouble(ocena));
        }
    }
}
