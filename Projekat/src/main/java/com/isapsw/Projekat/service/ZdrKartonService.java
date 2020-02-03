package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Klinika;
import com.isapsw.Projekat.domain.ZdrKarton;
import com.isapsw.Projekat.dto.KlinikaDTO;
import com.isapsw.Projekat.dto.ZdrKartonDTO;
import com.isapsw.Projekat.repository.ZdrKartonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZdrKartonService {


    @Autowired
    private ZdrKartonRepository zdrKartonRepository;

    public ZdrKarton findZdrKarton(String id) { return  zdrKartonRepository.findByPacijentId(Long.parseLong(id));}

    @Transactional(readOnly = false)
    public ZdrKarton editZdrKarton(ZdrKartonDTO zdrKartonDTO){
        ZdrKarton zdrKarton = zdrKartonRepository.findById(zdrKartonDTO.getId()).get();
        if(zdrKarton == null){
            return  zdrKarton;
        }
        if(zdrKarton.getVersion() != zdrKarton.getVersion()){
            return null;
        }
        zdrKarton.setTezina(zdrKartonDTO.getTezina());
        zdrKarton.setVisina(zdrKartonDTO.getVisina());
        zdrKarton.setDioptrija(zdrKartonDTO.getDioptrija());
        zdrKarton.setKrvnaGrupa(zdrKartonDTO.getKrvnaGrupa());

        zdrKartonRepository.save(zdrKarton);
        return zdrKarton;
    }
}
