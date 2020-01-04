package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.service.PregledOperacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/pregled_operacija")
public class PregledOperacijaController {

    @Autowired
    private PregledOperacijaService pregledOperacijaService;

    @PostMapping
    public ResponseEntity<String> zavrsiPregledOperaciju(@RequestBody Map<String,Object> body){
        try {

            String ret = pregledOperacijaService.zavrsiPregledOperaciju(body);

            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> zapocniPregledOperaciju(@RequestBody Map<String,Object> body){
        try {

            String ret = pregledOperacijaService.zapocniPregledOperaciju(body);

            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
