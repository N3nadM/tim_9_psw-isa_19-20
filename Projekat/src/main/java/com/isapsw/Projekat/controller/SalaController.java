package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.Sala;
import com.isapsw.Projekat.dto.SalaDTO;
import com.isapsw.Projekat.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/sala")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @PostMapping
    public ResponseEntity<Sala> addSala(@RequestBody SalaDTO salaDTO){
        try{
            return new ResponseEntity<Sala>(salaService.addSala(salaDTO), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}