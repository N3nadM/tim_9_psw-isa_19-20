package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Pacijent_Zahtev;
import com.isapsw.Projekat.repository.Pacijent_ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Pacijent_ZahtevService {

    @Autowired
    private Pacijent_ZahtevRepository pacijent_zahtevRepository;

    public List<Pacijent_Zahtev> findAll(){
        return pacijent_zahtevRepository.findAll();
    }
}
