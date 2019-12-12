package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.Lekar;
import com.isapsw.Projekat.domain.MedicinskaSestra;
import com.isapsw.Projekat.domain.Pregled;
import com.isapsw.Projekat.domain.TipPregleda;
import com.isapsw.Projekat.service.LekarService;
import com.isapsw.Projekat.service.MedSestraService;
import com.isapsw.Projekat.service.PregledService;
import org.h2.compress.LZFInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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


}
