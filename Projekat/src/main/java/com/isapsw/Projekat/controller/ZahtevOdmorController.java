package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.ZahtevOdmor;
import com.isapsw.Projekat.domain.ZahtevOdsustvo;
import com.isapsw.Projekat.dto.ZahtevOdmorDTO;
import com.isapsw.Projekat.dto.ZahtevOdsustvoDTO;
import com.isapsw.Projekat.service.ZahtevOdmorService;
import com.isapsw.Projekat.service.ZahtevOdsustvoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/zahtevOdmor")
public class ZahtevOdmorController {

    @Autowired
    private ZahtevOdmorService zahtevOdmorService;

    @GetMapping
    public ResponseEntity<List<ZahtevOdmor>> getAllZahteviOdmor(){
        try {

            List<ZahtevOdmor> zahteviOdmor = zahtevOdmorService.getAllZahteviOdmor();

            return new ResponseEntity<>(zahteviOdmor, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ZahtevOdmor> addZahtevOdmor(@RequestBody ZahtevOdmorDTO zahtevOdmorDTO){
        try {

            ZahtevOdmor zahtevOdmor = zahtevOdmorService.addZahtevOdmor(zahtevOdmorDTO);

            return new ResponseEntity<>(zahtevOdmor, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/naKlinici/{id}")
    public ResponseEntity<List<ZahtevOdmor>> getZahteviNaKlinici(@PathVariable String id){
        try {

            List<ZahtevOdmor> zahteviOdsustvo = zahtevOdmorService.getZahteviNaKlinici(id);

            return new ResponseEntity<>(zahteviOdsustvo, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/deny")
    public ResponseEntity<ZahtevOdmor> denyZahtev(@RequestBody Map<String,Object> body){
        try {

            ZahtevOdmor zahtevOdmor= zahtevOdmorService.denyZahtev(body.get("id").toString(), body.get("poruka").toString());

            return new ResponseEntity<ZahtevOdmor>(zahtevOdmor, HttpStatus.OK);
        } catch(Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<ZahtevOdmor> acceptZahtev(@PathVariable String id){
        try {

            ZahtevOdmor zahtevOdmor = zahtevOdmorService.acceptZahtev(id);
            return new ResponseEntity<ZahtevOdmor>(zahtevOdmor, HttpStatus.OK);
        } catch(Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
