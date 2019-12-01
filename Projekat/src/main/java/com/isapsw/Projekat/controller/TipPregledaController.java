package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.TipPregleda;
import com.isapsw.Projekat.service.TipPregledaService;
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
@RequestMapping("/api/tipPregleda")
public class TipPregledaController {

    @Autowired
    private TipPregledaService tipPregledaService;

    @GetMapping
    public ResponseEntity<List<TipPregleda>> getAllTipoviPregleda(){
        try{
            return new ResponseEntity<>(tipPregledaService.getAllTipoviPregleda(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
