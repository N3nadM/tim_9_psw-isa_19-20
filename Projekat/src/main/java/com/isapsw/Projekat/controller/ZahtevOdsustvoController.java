package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.OdsustvoDTO;
import com.isapsw.Projekat.dto.ZahtevOdsustvoDTO;
import com.isapsw.Projekat.service.LekarService;
import com.isapsw.Projekat.service.MedSestraService;
import com.isapsw.Projekat.service.ZahtevOdsustvoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/zahtevOdsustvo")
public class ZahtevOdsustvoController {

    @Autowired
    private ZahtevOdsustvoService zahtevOdsustvoService;


    @GetMapping
    public ResponseEntity<List<ZahtevOdsustvo>> getAllZahteviOdsustvo(){
        try {

            List<ZahtevOdsustvo> zahteviOdsustvo = zahtevOdsustvoService.getAllZahteviOdsustvo();

            return new ResponseEntity<>(zahteviOdsustvo, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ZahtevOdsustvo> addZahtevOdsustvo(@RequestBody ZahtevOdsustvoDTO zahtevOdsustvoDTO){
        try {

            ZahtevOdsustvo zahtevOdsustvo = zahtevOdsustvoService.addZahtevOdsustvo(zahtevOdsustvoDTO);

            return new ResponseEntity<>(zahtevOdsustvo, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/naKlinici/{id}")
    public ResponseEntity<List<ZahtevOdsustvo>> getZahteviNaKlinici(@PathVariable String id){
        try {

            List<ZahtevOdsustvo> zahteviOdsustvo = zahtevOdsustvoService.getZahteviNaKlinici(id);

            return new ResponseEntity<>(zahteviOdsustvo, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
