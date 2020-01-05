package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.Pregled;
import com.isapsw.Projekat.service.OperacijaService;
import com.isapsw.Projekat.service.PregledOperacijaService;
import com.isapsw.Projekat.service.PregledService;
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
    public ResponseEntity<Object> zavrsiPregledOperaciju(@RequestBody Map<String,Object> body){
        try {

            Object ret = pregledOperacijaService.zavrsiPregledOperaciju(body);

            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Object> zapocniPregledOperaciju(@RequestBody Map<String,Object> body){
        try {

            Object ret = pregledOperacijaService.zapocniPregledOperaciju(body);

            return new ResponseEntity<>(ret, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/izvestaj")
    public ResponseEntity<Object> izmeniIzvestaj(@RequestBody Map<String,Object> body){
        try {

            Object object = pregledOperacijaService.izmeniIzvestaj(body);

            return new ResponseEntity<>(object, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
