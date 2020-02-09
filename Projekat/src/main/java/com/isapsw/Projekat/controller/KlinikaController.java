package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.KlinikaDTO;
import com.isapsw.Projekat.service.KlinikaService;
import com.isapsw.Projekat.service.KorisnikService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/klinika")
public class KlinikaController {

    @Autowired
    private KlinikaService klinikaService;

    @Autowired
    private KorisnikService korisnikService;

    @GetMapping
    public ResponseEntity<List<Klinika>> getAllKlinike() {
        try {
            List<Klinika> klinike = klinikaService.getAllKlinike();
            return new ResponseEntity<>(klinike, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/ocenaPacijenta/{idKlinike}")
    @PreAuthorize("hasRole('ROLE_PACIJENT')")
    public ResponseEntity<Integer> getOcenaPacijenta(@PathVariable String idKlinike, Authentication authentication) {
        try {
            Korisnik korisnik = (Korisnik)authentication.getPrincipal();
            return new ResponseEntity<>(klinikaService.getOcenaKlinikeOdPacijenta(korisnik, idKlinike), HttpStatus.OK);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/oceni")
    @PreAuthorize("hasRole('ROLE_PACIJENT')")
    public ResponseEntity<OcenaKlinike> oceniKliniku(@RequestBody Map<String,Object> body, Authentication authentication) {
        try{
            Korisnik korisnik = (Korisnik)authentication.getPrincipal();
            return new ResponseEntity<OcenaKlinike>(klinikaService.oceniKliniku(body.get("id").toString(),body.get("ocena").toString(), korisnik), HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Klinika> getKlinikaById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(klinikaService.getKlinikaById(Long.parseLong(id)), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Klinika> addKlinika(@RequestBody Klinika klinika) {
        try{
            return new ResponseEntity<Klinika>(klinikaService.addKlinika(klinika), HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Klinika> editAccount(@RequestBody KlinikaDTO klinika) {
        Klinika k = klinikaService.editKlinika(klinika);
        if(k == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Klinika>(k, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Klinika>> getKlinikeWithSearch(@RequestBody Map<String,Object> body) {
        try {
            List<Klinika> klinike = klinikaService.searchKlinike(body.get("lokacija").toString(), body.get("ocena").toString(), body.get("tip").toString(), body.get("datum").toString());
            return new ResponseEntity<>(klinike, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getLekari/{id}")
    public ResponseEntity<List<Lekar>> getLekariKlinike(@PathVariable String id, @RequestBody Map<String,Object> body) {
        try {
            System.out.println(body.get("tip").toString());
            System.out.println(body.get("datum").toString());
            List<Lekar> lekars = klinikaService.getLekariKlinike(Long.parseLong(id), body.get("tip").toString(), body.get("datum").toString());
            return new ResponseEntity<>(lekars, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getLekariNaKlinici/{id}")
    public ResponseEntity<List<Lekar>> getLekariNaKlinici(@PathVariable String id) {
        try {
            List<Lekar> lekars = klinikaService.getLekariNaKlinici(Long.parseLong(id));
            return new ResponseEntity<>(lekars, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/searchLekariNaKlinici/{id}")
    public ResponseEntity<List<Lekar>> searchLekariNaKlinici(@PathVariable String id, @RequestBody Map<String,Object> body) {
        try {
            List<Lekar> lekars = klinikaService.searchLekariNaKlinici(Long.parseLong(id), body.get("imePretraga").toString(), body.get("prezimePretraga").toString(), body.get("emailPretraga").toString());
            return new ResponseEntity<>(lekars, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/prihod/{id}")
    public ResponseEntity<Double> getPrihod(@PathVariable String id, @RequestBody Map<String,Object> body) {
        try {
            Double prihod = klinikaService.getUkupanPrihod(id, body.get("datumOd").toString(), body.get("datumDo").toString());
            return new ResponseEntity<>(prihod, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/grafik/Dan/{id}")
    public ResponseEntity<HashMap<String, Integer>> zaGrafikDan(@PathVariable String id) {
        try {
            HashMap<String,Integer> podaci = klinikaService.preglediGrafikDan(id);
            return new ResponseEntity<>(podaci, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/grafik/Nedelja/{id}")
    public ResponseEntity<HashMap<String, Integer>> zaGrafikNedelja(@PathVariable String id) {
        try {
            HashMap<String,Integer> podaci = klinikaService.preglediGrafikNedelja(id);
            return new ResponseEntity<>(podaci, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/grafik/Mesec/{id}")
    public ResponseEntity<HashMap<String, Integer>> zaGrafikMesec(@PathVariable String id) {
        try {
            HashMap<String,Integer> podaci = klinikaService.preglediGrafikMesec(id);
            return new ResponseEntity<>(podaci, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
