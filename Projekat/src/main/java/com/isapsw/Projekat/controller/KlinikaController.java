package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.Klinika;
import com.isapsw.Projekat.service.KlinikaService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/klinika")
public class KlinikaController {

    @Autowired
    private KlinikaService klinikaService;

    @GetMapping //parametri kasnije za pagination
    public ResponseEntity<List<Klinika>> getAllKlinike() {
        try {
            List<Klinika> klinike = klinikaService.getAllKlinike();
            return new ResponseEntity<>(klinike, HttpStatus.OK);
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

    @PostMapping("/search")
    public ResponseEntity<List<Klinika>> getKlinikeWithSearch(@RequestBody Map<String,Object> body) {
        try {
            List<Klinika> klinike = klinikaService.searchKlinike(body.get("lokacija").toString(), body.get("ocena").toString());
            return new ResponseEntity<>(klinike, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
