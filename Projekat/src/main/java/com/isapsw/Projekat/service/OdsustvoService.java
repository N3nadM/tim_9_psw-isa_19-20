package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Odsustvo;
import com.isapsw.Projekat.repository.OdsustvoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OdsustvoService {

    @Autowired
    private OdsustvoRepository odsustvoRepository;

    public Odsustvo addOdsustvo(Odsustvo odsustvo){ return odsustvoRepository.save(odsustvo); }
}
