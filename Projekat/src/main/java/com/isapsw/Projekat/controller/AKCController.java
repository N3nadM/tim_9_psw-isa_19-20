package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.AdminKlinCentra;
import com.isapsw.Projekat.domain.AdminKlinike;
import com.isapsw.Projekat.domain.Zahtev;
import com.isapsw.Projekat.dto.AdminKCDTO;
import com.isapsw.Projekat.service.AKCService;
import com.isapsw.Projekat.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/adminkc")
public class AKCController {

    @Autowired
    private AKCService akcService;

    @Autowired
    private ZahtevService zahtevService;

    @GetMapping(value = "/zahtevi")
    public ResponseEntity<List<Zahtev>> getAllRequestsOfPatients(){

        List<Zahtev> zahteviPacijenata = zahtevService.findAll();

        return new ResponseEntity<>(zahteviPacijenata, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AdminKlinCentra> addNewAKC(@RequestBody AdminKCDTO adminKCDTO){

        try{
            return new ResponseEntity<AdminKlinCentra>(akcService.createAdminKC(adminKCDTO), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/korisnik/{id}")
    public ResponseEntity<AdminKlinCentra> getAdminKlinickogCentra(@PathVariable String id){

        try{
            AdminKlinCentra adminKlinCentra = akcService.getAdminByKorisnikId(id);
            return new ResponseEntity<AdminKlinCentra>(adminKlinCentra, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
