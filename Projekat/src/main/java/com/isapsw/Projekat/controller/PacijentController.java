package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.Pacijent;
import com.isapsw.Projekat.service.PacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/pacijent")
public class PacijentController {
    @Autowired
    private PacijentService pacijentService;

    @GetMapping("/{id}")
    public ResponseEntity<Pacijent> confirmAccount(@PathVariable String id) {

        Pacijent pacijent = pacijentService.findPacijent(id);

        return new ResponseEntity<Pacijent>(pacijent, HttpStatus.OK);
    }
}
