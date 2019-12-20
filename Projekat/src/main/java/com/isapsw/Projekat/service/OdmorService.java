package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Odmor;
import com.isapsw.Projekat.repository.OdmorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OdmorService {

    @Autowired
    private OdmorRepository odmorRepository;

    public Odmor addOdmor(Odmor odmor){ return odmorRepository.save(odmor); }
}
