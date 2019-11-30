package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.Operacija;
import com.isapsw.Projekat.service.OperacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/operacija")
public class OperacijaController {
    @Autowired
    private OperacijaService operacijaService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Operacija>> confirmAccount(@PathVariable String id) {
        try {
            List<Operacija> pregledi = operacijaService.getOperacijeByPacijentId(Long.parseLong(id));
            return new ResponseEntity<List<Operacija>>(pregledi, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
