package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.TipPregleda;
import com.isapsw.Projekat.dto.TipPregledaDTO;
import com.isapsw.Projekat.service.TipoviPregledaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
            System.out.println(e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<TipPregleda> addNewTip(@RequestBody TipPregledaDTO tipPregledaDTO){
        try{
            System.out.println("uslo ovde");
            TipPregleda t = tipoviPregledaService.addTipPregleda(tipPregledaDTO);
            return new ResponseEntity<TipPregleda>(t, HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
