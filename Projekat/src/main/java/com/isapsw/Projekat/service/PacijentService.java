package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Pacijent;
import com.isapsw.Projekat.repository.PacijentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacijentService {

    @Autowired
    private PacijentRepository pacijentRepository;

    public Pacijent findPacijent(String id) {
        return pacijentRepository.findPacijentByKorisnikId(Long.parseLong(id));
    }
}
