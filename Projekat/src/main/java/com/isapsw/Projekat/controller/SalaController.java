package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.Lekar;
import com.isapsw.Projekat.domain.Sala;
import com.isapsw.Projekat.dto.SalaDTO;
import com.isapsw.Projekat.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/sala")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @PostMapping
    public ResponseEntity<Sala> addSala(@RequestBody SalaDTO salaDTO){
        try{
            return new ResponseEntity<Sala>(salaService.addSala(salaDTO), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/saleNaKlinici/{id}")
    public ResponseEntity<List<Sala>> getSaleNaKlinici(@PathVariable String id){
        try{
            return  new ResponseEntity<List<Sala>>(salaService.getSaleNaKlinici(id), HttpStatus.OK);
        }catch(Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/dostupneSale/{id}/{termin}/{trajanje}")
    public ResponseEntity<List<Sala>> getDostupneSale(@PathVariable String id, @PathVariable String termin, @PathVariable String trajanje){
        try{
            return  new ResponseEntity<List<Sala>>(salaService.getDostupneSale(id, termin, trajanje), HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/search/{id}")
    public ResponseEntity<List<Sala>> searcSaleNaKlinici(@PathVariable String id, @RequestBody Map<String,Object> body) {
        try {
            List<Sala> salas = salaService.search(Long.parseLong(id), body.get("broj").toString(), body.get("naziv").toString());
            return new ResponseEntity<>(salas, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/saleZaBrisanje/{id}")
    public ResponseEntity<List<Sala>> getSaleKojeSeMoguObrisati(@PathVariable String id) {
        try {
            List<Sala> salas = salaService.getSaleKojeSeMoguObrisati(id);
            return new ResponseEntity<>(salas, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Sala> addSala(@PathVariable String id){
        try{
            return new ResponseEntity<Sala>(salaService.obrisiSalu(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Sala> editSala(@RequestBody SalaDTO salaDTO){
        try{
            return new ResponseEntity<Sala>(salaService.editSala(salaDTO), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
