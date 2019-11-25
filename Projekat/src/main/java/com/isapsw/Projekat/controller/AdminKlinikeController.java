package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.AdminKlinike;
import com.isapsw.Projekat.service.AdminKlinikeService;
import com.isapsw.Projekat.service.KlinikaService;
import com.isapsw.Projekat.service.KorisnikService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/adminK")
public class AdminKlinikeController {

    @Autowired
    private AdminKlinikeService adminKlinikeService;

    @Autowired
    private KlinikaService klinikaService;

    @Autowired
    private KorisnikService korisnikService;

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
    public ResponseEntity<AdminKlinike> addAdminK(@RequestBody AdminKlinike adminK) {
        try{
            Korisnik k = new Korisnik;
            adminK.setKlinika(klinikaService.findKlinikaId(1L).get());
            return new ResponseEntity<AdminKlinike>(adminKlinikeService.save(adminK), HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
