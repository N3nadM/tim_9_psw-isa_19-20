package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.Authority;
import com.isapsw.Projekat.domain.Korisnik;
import com.isapsw.Projekat.domain.AdminKlinike;
import com.isapsw.Projekat.dto.KorisnikDTO;
import com.isapsw.Projekat.service.AdminKlinikeService;
import com.isapsw.Projekat.service.AuthorityService;
import com.isapsw.Projekat.service.KlinikaService;
import com.isapsw.Projekat.service.KorisnikService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/adminK")
public class AdminKlinikeController {

    @Autowired
    private AdminKlinikeService adminKlinikeService;

    @Autowired
    private KlinikaService klinikaService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private AuthorityService authorityService;

    @GetMapping
    public ResponseEntity<List<AdminKlinike>> getAllAdmins(){

        try {
            List<AdminKlinike> akList = adminKlinikeService.findAll();
            return new ResponseEntity<>(akList, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AdminKlinike> getAdminKId(@PathVariable Long id){

        AdminKlinike ak = adminKlinikeService.findById(id).get();

        try{
            return new ResponseEntity<AdminKlinike>(ak, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<AdminKlinike> addAdminK(@RequestBody KorisnikDTO korisnikDTO) {
        try{
            AdminKlinike ak = new AdminKlinike();

            Korisnik k = new Korisnik();
            k.setIme(korisnikDTO.getIme());
            k.setPrezime(korisnikDTO.getPrezime());
            k.setAdresa(korisnikDTO.getAdresa());
            k.setDrzava(korisnikDTO.getDrzava());
            k.setGrad(korisnikDTO.getGrad());
            k.setTelefon(korisnikDTO.getTelefon());
            k.setEmail(korisnikDTO.getEmail());
            k.setPassword(korisnikDTO.getPassword());

            Authority a = authorityService.findByName("ROLE_AK");
            List<Authority> authorities = new ArrayList<>();
            authorities.add(a);

            k.setAuthorities(authorities);
            korisnikService.addKorisnik(k);

            ak.setKorisnik(k);
            ak.setKlinika(klinikaService.findKlinikaId(1L).get());
            return new ResponseEntity<AdminKlinike>(adminKlinikeService.save(ak), HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
