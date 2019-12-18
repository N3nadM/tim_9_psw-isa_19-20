package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.PregledDTO;
import com.isapsw.Projekat.service.LekarService;
import com.isapsw.Projekat.service.MedSestraService;
import com.isapsw.Projekat.service.PregledService;
import org.h2.compress.LZFInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/pregled")
public class PregledController {
    @Autowired
    private PregledService pregledService;

    @Autowired
    private LekarService lekarService;

    @Autowired
    private MedSestraService medSestraService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Pregled>> getPregledByPacijentId(@PathVariable String id) {
        try {
            List<Pregled> pregledi = pregledService.getPreglediByPacijentId(Long.parseLong(id));
            return new ResponseEntity<List<Pregled>>(pregledi, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/osoblje/{id}")
    public ResponseEntity<List<Pregled>> getPregledByLekarId(@PathVariable String id) {
        try {
            Lekar lekar = lekarService.findLekar(id);
            MedicinskaSestra medicinskaSestra = medSestraService.findMedSestra(id);
            List<Pregled> pregledi = new ArrayList<>();

            if(lekar != null)
                pregledi = pregledService.getPreglediByLekarId(lekar.getId());
            else if(medicinskaSestra != null)
                pregledi = pregledService.getPreglediByMedSestraId(medicinskaSestra.getId());


            return new ResponseEntity<List<Pregled>>(pregledi, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sala/{id}")
    public ResponseEntity<List<Pregled>> getPregledBySalaId(@PathVariable String id) {
        try {
            return new ResponseEntity<List<Pregled>>(pregledService.getPreglediBySalaId(Long.parseLong(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Pregled> addPregled(@RequestBody PregledDTO pregledDTO) {
        try {

            System.out.println(pregledDTO.getDatum());

            return new ResponseEntity<Pregled>(pregledService.addPregled(pregledDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/predefinisani/{id}")
    public ResponseEntity<List<Pregled>> getPredefinisaniPregledi(@PathVariable String id) {
        try {
            return new ResponseEntity<List<Pregled>>(pregledService.getPredefinisaniPregledi(Long.parseLong(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/zakaziPregled")
    public ResponseEntity<Boolean> zakaziPregled(@RequestBody Map<String,String> body, Authentication authentication) {
        try {
            Korisnik korisnik = (Korisnik)authentication.getPrincipal();
            return new ResponseEntity(pregledService.zakaziPregled(korisnik.getId(), body.get("lekarId").toString(), body.get("datum").toString()), HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/otkaziPregled")
    public ResponseEntity<Boolean> otkaziPregled(@RequestBody Map<String,String> body, Authentication authentication) {
        try {
            Korisnik korisnik = (Korisnik)authentication.getPrincipal();
            return new ResponseEntity(pregledService.otkaziPregled(korisnik.getId(), body.get("pregledId").toString()), HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/zakaziPredefinisani")
    public ResponseEntity<Boolean> zakaziPredefinisani(@RequestBody Map<String,String> body, Authentication authentication) {
        try {
            Korisnik korisnik = (Korisnik)authentication.getPrincipal();
            return new ResponseEntity(pregledService.zakaziPredefinisaniPregled(korisnik.getId(), body.get("pregledId").toString()), HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
