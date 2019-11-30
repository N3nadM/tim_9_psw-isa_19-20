package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Klinika;
import com.isapsw.Projekat.dto.KlinikaDTO;
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


    public List<Klinika> searchKlinike(String lokacija, String ocena) {
        if(ocena.isEmpty()) {
            return klinikaRepository.findKlinikaByAdresaContaining(lokacija.toUpperCase());
        } else {
            return klinikaRepository.findKlinikaByAdresaAndOcena(lokacija.toUpperCase(), Double.parseDouble(ocena));
        }
    }
  
    public Optional<Klinika> findKlinikaId(Long id){
        return klinikaRepository.findById(id);
    }

    public Klinika editKlinika(KlinikaDTO klinika){
        Klinika k = klinikaRepository.findById(klinika.getId()).get();
        if(k == null){
            return  k;
        }
        k.setAdresa(klinika.getAdresa());
        k.setNaziv(klinika.getNaziv());
        k.setOpis(klinika.getOpis());
        klinikaRepository.save(k);
        return k;
    }

    public Klinika getKlinikaById(Long id) {
        return klinikaRepository.findKlinikaById(id);
    }
}
