package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.MedicinskaSestra;
import com.isapsw.Projekat.domain.Operacija;
import com.isapsw.Projekat.domain.Recept;
import com.isapsw.Projekat.service.MedSestraService;
import com.isapsw.Projekat.service.ReceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/recept")
public class ReceptController {

    @Autowired
    private ReceptService receptService;

    @Autowired
    private MedSestraService medSestraService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Recept>> getReceptiZaOveru(@PathVariable String id){
        try {

            MedicinskaSestra medicinskaSestra = medSestraService.findMedSestra(id);

            List<Recept> receptiZaOveru = receptService.findAllByMedSestraId(medicinskaSestra.getId());

            System.out.println(receptiZaOveru.size());

            return new ResponseEntity<>(receptiZaOveru, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/overi/{id}")
    public ResponseEntity<Recept> overiRecept(@PathVariable String id){
        try {

            Recept recept = receptService.overiRecept(Long.parseLong(id));

            return new ResponseEntity<>(recept, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
