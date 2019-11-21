package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.MedicinskaSestra;
import com.isapsw.Projekat.repository.MedSestraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedSestraService {

    @Autowired
    MedSestraRepository medSestraRepository;

    public MedicinskaSestra findMedSestra(String id){
        return  medSestraRepository.findMedicinskaSestraByKorisnikId(Long.parseLong(id));
    }
}
