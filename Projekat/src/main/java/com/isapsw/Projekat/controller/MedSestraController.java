package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.MedicinskaSestra;
import com.isapsw.Projekat.domain.Sala;
import com.isapsw.Projekat.service.MedSestraService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medsestra")
public class MedSestraController {

    @Autowired
    MedSestraService medSestraService;

    @GetMapping("/{id}")
    public ResponseEntity<MedicinskaSestra> confirmAccount(@PathVariable String id){
        MedicinskaSestra ms = medSestraService.findMedSestra(id);
        return  new ResponseEntity<MedicinskaSestra>(ms, HttpStatus.OK);
    }

    @GetMapping("/dostupneSestre/{id}/{termin}/{trajanje}")
    public ResponseEntity<List<MedicinskaSestra>> getDostupneSestre(@PathVariable String id, @PathVariable String termin, @PathVariable String trajanje){
        try{
            System.out.println(id);
            System.out.println(termin);
            return  new ResponseEntity<List<MedicinskaSestra>>(medSestraService.getDostupneSestre(id, termin, trajanje), HttpStatus.OK);
        }catch(Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sestraDostupna/{id}/{termin}/{trajanje}")
    public ResponseEntity<MedicinskaSestra> getDostupnaSestra(@PathVariable String id, @PathVariable String termin, @PathVariable String trajanje){
        try{
            return  new ResponseEntity<MedicinskaSestra>(medSestraService.getDostupnaSestra(id, termin, trajanje), HttpStatus.OK);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
