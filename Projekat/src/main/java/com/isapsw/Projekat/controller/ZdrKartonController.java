package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.ZdrKarton;
import com.isapsw.Projekat.service.ZdrKartonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/karton")
public class ZdrKartonController {

    @Autowired
    private ZdrKartonService zdrKartonService;

    @GetMapping("/{id}")
    public ResponseEntity<ZdrKarton> findZdrKarton(@PathVariable String id) {
        try {
            ZdrKarton zdrKarton = zdrKartonService.findZdrKarton(id);
            return new ResponseEntity<ZdrKarton>(zdrKarton, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}