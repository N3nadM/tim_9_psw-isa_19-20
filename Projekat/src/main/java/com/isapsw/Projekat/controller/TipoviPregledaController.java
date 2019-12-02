package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.TipPregleda;
import com.isapsw.Projekat.service.TipoviPregledaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/tipovi")
public class TipoviPregledaController {

    @Autowired
    private TipoviPregledaService tipoviPregledaService;

    @GetMapping
    public ResponseEntity<List<String>> getAllTipoviPregleda() {
        try {
            List<String> tipPregleda = tipoviPregledaService.getAllTipoviPregleda();
            return new ResponseEntity<List<String>>(tipPregleda, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
