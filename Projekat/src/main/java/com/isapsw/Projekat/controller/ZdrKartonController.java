package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.KlinikaDTO;
import com.isapsw.Projekat.dto.ZdrKartonDTO;
import com.isapsw.Projekat.service.ZdrKartonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/karton")
public class ZdrKartonController {

    @Autowired
    private ZdrKartonService zdrKartonService;

    @GetMapping("/{id}")
    public ResponseEntity<ZdrKarton> findZdrKarton(@PathVariable String id, Authentication authentication) {
        try {
            Korisnik korisnik = (Korisnik)authentication.getPrincipal();
            ZdrKarton zdrKarton = zdrKartonService.findZdrKarton(id);
            if(korisnik.getId() != zdrKarton.getPacijent().getKorisnik().getId()) {
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<ZdrKarton>(zdrKarton, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ZdrKarton> editZdrKarton(@RequestBody ZdrKartonDTO zdrKartonDTO) {
        try{
            ZdrKarton zdrKarton = zdrKartonService.editZdrKarton(zdrKartonDTO);
            return new ResponseEntity<ZdrKarton>(zdrKarton, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
