package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.MedicinskaSestra;
import com.isapsw.Projekat.service.MedSestraService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
}
