package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.TipPregleda;
import com.isapsw.Projekat.dto.TipPregledaDTO;
import com.isapsw.Projekat.service.TipPregledaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/tipPregleda")
public class TipPregledaController {

    @Autowired
    private TipPregledaService tipPregledaService;

    @GetMapping("/getTipoviNaKlinici/{id}")
    public ResponseEntity<List<TipPregleda>> getAllTipoviPregleda(@PathVariable String id){
        try{
            return new ResponseEntity<>(tipPregledaService.getTipoviSaKlinike(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/search/{id}")
    public ResponseEntity<List<TipPregleda>> searchTipoviNaKlinici(@PathVariable String id, @RequestBody Map<String,Object> body) {
        try {
            List<TipPregleda> tipovi = tipPregledaService.searchTipoviNaKlinici(Long.parseLong(id), body.get("naziv").toString(), body.get("cenaPregleda").toString(),body.get("minimalnoTrajanjeMin").toString());
            return new ResponseEntity<>(tipovi, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/tipoviZaIzmenu/{id}")
    public ResponseEntity<List<TipPregleda>> getTipoviZaIzmenu(@PathVariable String id){
        try{
            return new ResponseEntity<>(tipPregledaService.getTipoviZaIzmenu(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<TipPregleda> editTip(@RequestBody TipPregledaDTO tipPregledaDTO){
        try{
            return  new ResponseEntity<TipPregleda>(tipPregledaService.editTip(tipPregledaDTO), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
