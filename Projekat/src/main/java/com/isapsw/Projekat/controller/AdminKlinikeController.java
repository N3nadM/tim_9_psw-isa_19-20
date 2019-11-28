package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.KorisnikDTO;
import com.isapsw.Projekat.service.AdminKlinikeService;
import com.isapsw.Projekat.service.AuthorityService;
import com.isapsw.Projekat.service.KlinikaService;
import com.isapsw.Projekat.service.KorisnikService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/adminK")
public class AdminKlinikeController {

    @Autowired
    private AdminKlinikeService adminKlinikeService;

    @GetMapping
    public ResponseEntity<List<AdminKlinike>> getAllAdmins(){

        try {
            List<AdminKlinike> akList = adminKlinikeService.findAll();
            return new ResponseEntity<>(akList, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AdminKlinike> getAdminKId(@PathVariable Long id){

        AdminKlinike ak = adminKlinikeService.findById(id).get();

        try{
            return new ResponseEntity<AdminKlinike>(ak, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<AdminKlinike> addAdminK(@RequestBody KorisnikDTO korisnikDTO) {
        try{

            return new ResponseEntity<AdminKlinike>(adminKlinikeService.createAdminKlinike(korisnikDTO), HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getKlinika/{id}")
    public ResponseEntity<Klinika> getKlinikaAdmin(@PathVariable Long id){

        AdminKlinike ak = adminKlinikeService.findByKorisnikId(id).get();
        Klinika k = ak.getKlinika();
        try{
            return new ResponseEntity<Klinika>(k, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
