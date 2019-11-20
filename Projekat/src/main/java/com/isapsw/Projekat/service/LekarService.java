package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Lekar;
import com.isapsw.Projekat.repository.LekarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LekarService {

    @Autowired
    private LekarRepository lekarRepository;

    public Lekar findLekar(String id) {
        return lekarRepository.findLekarByKorisnikId(Long.parseLong(id));
    }
}
