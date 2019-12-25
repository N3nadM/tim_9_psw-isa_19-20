package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.Lekar;
import com.isapsw.Projekat.domain.MedicinskaSestra;
import com.isapsw.Projekat.domain.Operacija;
import com.isapsw.Projekat.domain.Pregled;
import com.isapsw.Projekat.service.LekarService;
import com.isapsw.Projekat.service.MedSestraService;
import com.isapsw.Projekat.service.OperacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/operacija")
public class OperacijaController {
    @Autowired
    private OperacijaService operacijaService;

    @Autowired
    private LekarService lekarService;

    @Autowired
    private MedSestraService medSestraService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Operacija>> confirmAccount(@PathVariable String id) {
        try {
            List<Operacija> operacije = operacijaService.getOperacijeByPacijentId(Long.parseLong(id));
            return new ResponseEntity<List<Operacija>>(operacije, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/osoblje/{id}")
    public ResponseEntity<List<Operacija>> findOperacijeByLekarId(@PathVariable String id) {
        try {
            Lekar lekar = lekarService.findLekar(id);
            MedicinskaSestra medicinskaSestra = medSestraService.findMedSestra(id);
            List<Operacija> operacije = new ArrayList<>();

            if(lekar != null)
                operacije = operacijaService.getOperacijeByLekarId(lekar.getId());
            else if(medicinskaSestra != null)
                operacije = operacijaService.getOperacijeByMedSestraId(medicinskaSestra.getId());

            return new ResponseEntity<List<Operacija>>(operacije, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sala/{id}")
    public ResponseEntity<List<Operacija>> findOperacijeBySalaId(@PathVariable String id) {
        try {
            return new ResponseEntity<List<Operacija>>(operacijaService.getOperacijeBySalaId(Long.parseLong(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/zaSestru/{id}/{datum}")
    public ResponseEntity<List<Operacija>> getSestraOperacije(@PathVariable String id, @PathVariable String datum) {
        try {
            return new ResponseEntity<List<Operacija>>(operacijaService.findOperacijeZaSestru(id, datum), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/zaLekara/{id}/{datum}")
    public ResponseEntity<List<Operacija>> getLekarOperacije(@PathVariable String id, @PathVariable String datum) {
        try {
            return new ResponseEntity<List<Operacija>>(operacijaService.findOperacijeZaLekara(id, datum), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/zaSestruOdmor/{id}/{datum1}/{datum2}")
    public ResponseEntity<List<Operacija>> getSestraOperacijeOdmor(@PathVariable String id, @PathVariable String datum1, @PathVariable String datum2) {
        try {
            return new ResponseEntity<List<Operacija>>(operacijaService.findOperacijeZaSestruOdmor(id, datum1, datum2), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/zaLekaraOdmor/{id}/{datum1}/{datum2}")
    public ResponseEntity<List<Operacija>> getLekarOperacijeOdmor(@PathVariable String id, @PathVariable String datum1, @PathVariable String datum2) {
        try {
            return new ResponseEntity<List<Operacija>>(operacijaService.findOperacijeZaLekaraOdmor(id, datum1, datum2), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
