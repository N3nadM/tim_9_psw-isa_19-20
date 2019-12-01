package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.Pregled;
import com.isapsw.Projekat.service.PregledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/pregled")
public class PregledController {
    @Autowired
    private PregledService pregledService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Pregled>> confirmAccount(@PathVariable String id) {
        try {
            List<Pregled> pregledi = pregledService.getPreglediByPacijentId(Long.parseLong(id));
            return new ResponseEntity<List<Pregled>>(pregledi, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


}
