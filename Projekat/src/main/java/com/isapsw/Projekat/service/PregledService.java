package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Pregled;
import com.isapsw.Projekat.repository.PregledRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PregledService {

    @Autowired
    private PregledRepository pregledRepository;

    public List<Pregled> getPreglediByPacijentId(Long id) {
        return pregledRepository.findPregledByPacijentId(id);
    }

}
