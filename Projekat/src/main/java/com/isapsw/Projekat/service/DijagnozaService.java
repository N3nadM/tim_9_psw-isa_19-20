package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Dijagnoza;
import com.isapsw.Projekat.domain.Lek;
import com.isapsw.Projekat.dto.DijagnozaDTO;
import com.isapsw.Projekat.repository.DijagnozaRepository;
import com.isapsw.Projekat.repository.LekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DijagnozaService {

    @Autowired
    private LekRepository lekRepository;

    @Autowired
    private DijagnozaRepository dijagnozaRepository;

    public List<Dijagnoza> findAllDijagnoze() { return dijagnozaRepository.findAll(); }

    public Dijagnoza addDijagnoza(DijagnozaDTO dijagnozaDTO){
        List<Lek> lekovi = new ArrayList<>();
        List<Lek> lekoviTemp = new ArrayList<>();

        for(String naziv : dijagnozaDTO.getLekovi()){
            lekovi.add(lekRepository.findLekByNaziv(naziv));
            lekoviTemp.add(lekRepository.findLekByNaziv(naziv));
        }

        Dijagnoza dijagnoza = dijagnozaRepository.save(new Dijagnoza(dijagnozaDTO, lekovi));

        for(Lek lek : lekoviTemp){
            lekovi.get(lekoviTemp.indexOf(lek)).getDijagnoze().add(dijagnoza);
            lekRepository.save(lekovi.get(lekoviTemp.indexOf(lek)));

        }

        return dijagnoza; }

}
