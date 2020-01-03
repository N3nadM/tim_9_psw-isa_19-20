package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Recept;
import com.isapsw.Projekat.repository.ReceptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceptService {

    @Autowired
    private ReceptRepository receptRepository;

    public List<Recept> findAllByMedSestraId(Long id){

        List<Recept> recepti = receptRepository.findAllByMedicinskaSestraId(id);

        List<Recept> receptiZaOveru = new ArrayList<>();

        for(Recept r : recepti){
            if(r.isOveren() == false){
                receptiZaOveru.add(r);
            }
        }

        return receptiZaOveru;
    }

    public Recept overiRecept(Long id) {

        Recept recept = receptRepository.findById(id).get();

        recept.setOveren(true);

        receptRepository.save(recept);

        return recept;
    }
}
