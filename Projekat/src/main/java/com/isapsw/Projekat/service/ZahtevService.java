package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Zahtev;
import com.isapsw.Projekat.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZahtevService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ZahtevRepository zahtevRepository;

    public List<Zahtev> findAll(){
        return zahtevRepository.findAll();
    }

    public void deleteById(Long id){
        zahtevRepository.deleteById(id);
    }

    public Zahtev saveZahtev(Zahtev newZahtev) {
        try {
            newZahtev.setPassword(bCryptPasswordEncoder.encode(newZahtev.getPassword()));

            return zahtevRepository.save(newZahtev);
        } catch(Exception e) {
            throw e;
        }
    }
}
