package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Zahtev;
import com.isapsw.Projekat.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZahtevService {

    @Autowired
    private ZahtevRepository zahtevRepository;

    public List<Zahtev> findAll(){
        return zahtevRepository.findAll();
    }

    public void deleteById(Long id){
        zahtevRepository.deleteById(id);
    }
}
