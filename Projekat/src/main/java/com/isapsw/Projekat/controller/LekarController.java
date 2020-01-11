package com.isapsw.Projekat.controller;
import com.isapsw.Projekat.domain.Korisnik;
import com.isapsw.Projekat.domain.Lek;
import com.isapsw.Projekat.domain.Lekar;
import com.isapsw.Projekat.domain.OcenaLekara;
import com.isapsw.Projekat.dto.LekarDTO;
import com.isapsw.Projekat.service.LekarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/lekar")
public class LekarController {

    @Autowired
    private LekarService lekarService;

    @GetMapping("/{id}")
    public ResponseEntity<Lekar> confirmAcount(@PathVariable String id){
        Lekar lekar = lekarService.findLekar(id);
        return  new ResponseEntity<Lekar>(lekar, HttpStatus.OK);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<Lekar> getLekarById(@PathVariable String id){
        Lekar lekar = lekarService.getLekarById(id);
        return  new ResponseEntity<Lekar>(lekar, HttpStatus.OK);
    }

    @PostMapping("/getTermini")
    public ResponseEntity<List<String>> getSlobodniTermini(@RequestBody Map<String,Object> body){
        try {
            List<String> termini = lekarService.findSlobodniTermini(Long.parseLong(body.get("id").toString()), body.get("datum").toString());
            return new ResponseEntity<>(termini, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Lekar> addLekar(@RequestBody LekarDTO lekar){
        try{
            return new ResponseEntity<Lekar>(lekarService.createLekar(lekar), HttpStatus.OK);
        }catch(Exception e){
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editLekarByAdmin/{id}")
    public ResponseEntity<Lekar> editLekarByAdmin(@PathVariable String id,@RequestBody LekarDTO lekar){
        try{
            return new ResponseEntity<Lekar>(lekarService.editLekarByAdmin(id,lekar), HttpStatus.OK);
        }catch(Exception e){
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/ocenaPacijenta/{idLekara}")
    @PreAuthorize("hasRole('ROLE_PACIJENT')")
    public ResponseEntity<Integer> getOcenaPacijenta(@PathVariable String idLekara, Authentication authentication) {
        try {
            Korisnik korisnik = (Korisnik)authentication.getPrincipal();
            return new ResponseEntity<>(lekarService.getOcenaLekaraOdPacijenta(korisnik, idLekara), HttpStatus.OK);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/oceni")
    @PreAuthorize("hasRole('ROLE_PACIJENT')")
    public ResponseEntity<OcenaLekara> oceniLekara(@RequestBody Map<String,Object> body, Authentication authentication) {
        try{
            Korisnik korisnik = (Korisnik)authentication.getPrincipal();
            return new ResponseEntity<OcenaLekara>(lekarService.oceniLekara(body.get("id").toString(),body.get("ocena").toString(), korisnik), HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/ocenePacijenta")
    public ResponseEntity<List<OcenaLekara>> getPacijenoveOceneLekara(@RequestBody Map<String,Object> body, Authentication authentication) {
        try{
            Korisnik korisnik = (Korisnik)authentication.getPrincipal();
            return new ResponseEntity<List<OcenaLekara>>(lekarService.getOceneLekara(body.get("ocene").toString(), korisnik), HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/lekariZaBrisanje/{id}")
    public ResponseEntity<List<Lekar>> getlekariKojiSeMoguObristi(@PathVariable String id){
        try{
            return new ResponseEntity<List<Lekar>>(lekarService.getLekartKojiSeMeoguObrisati(id), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Lekar> obrisiLekara(@PathVariable String id){
        try{
            return new ResponseEntity<Lekar>(lekarService.obrisiLekara(id), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
