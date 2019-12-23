package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.Dijagnoza;
import com.isapsw.Projekat.domain.Klinika;
import com.isapsw.Projekat.domain.Lek;
import com.isapsw.Projekat.dto.DijagnozaDTO;
import com.isapsw.Projekat.service.DijagnozaService;
import com.isapsw.Projekat.service.LekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/dijagnoza")
public class DijagnozaController {

    @Autowired
    private DijagnozaService dijagnozaService;

    @Autowired
    private LekService lekService;

    @PostMapping
    public ResponseEntity<Dijagnoza> addDijagnoza(@RequestBody DijagnozaDTO dijagnozaDTO){
        try {

            System.out.println(dijagnozaDTO.getLekovi().toString());
            List<Lek> lekovi = new ArrayList<>();

            for(String naziv : dijagnozaDTO.getLekovi()){
                lekovi.add(lekService.getLekByNaziv(naziv));
            }

            Dijagnoza dijagnoza = new Dijagnoza(dijagnozaDTO, lekovi);

            dijagnozaService.addDijagnoza(dijagnoza);

            return new ResponseEntity<>(dijagnoza, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}