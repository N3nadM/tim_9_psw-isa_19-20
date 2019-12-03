package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.Klinika;
import com.isapsw.Projekat.domain.Lekar;
import com.isapsw.Projekat.dto.KlinikaDTO;
import com.isapsw.Projekat.service.KlinikaService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/klinika")
public class KlinikaController {

    @Autowired
    private KlinikaService klinikaService;

    @GetMapping
    public ResponseEntity<List<Klinika>> getAllKlinike() {
        try {
            List<Klinika> klinike = klinikaService.getAllKlinike();
            return new ResponseEntity<>(klinike, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Klinika> getKlinikaById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(klinikaService.getKlinikaById(Long.parseLong(id)), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Klinika> addKlinika(@RequestBody Klinika klinika) {
        try{
            return new ResponseEntity<Klinika>(klinikaService.addKlinika(klinika), HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Klinika> editAccount(@RequestBody KlinikaDTO klinika) {
        Klinika k = klinikaService.editKlinika(klinika);
        if(k == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Klinika>(k, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Klinika>> getKlinikeWithSearch(@RequestBody Map<String,Object> body) {
        try {
            List<Klinika> klinike = klinikaService.searchKlinike(body.get("lokacija").toString(), body.get("ocena").toString(), body.get("tip").toString(), body.get("datum").toString());
            return new ResponseEntity<>(klinike, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getLekari/{id}")
    public ResponseEntity<List<Lekar>> getLekariKlinike(@PathVariable String id, @RequestBody Map<String,Object> body) {
        try {
            System.out.println("Usao sam");
            List<Lekar> lekars = klinikaService.getLekariKlinike(Long.parseLong(id), body.get("tip").toString(), body.get("datum").toString());
            return new ResponseEntity<>(lekars, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }



}
