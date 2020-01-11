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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
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

    @GetMapping("/zahtev/{id}")
    public ResponseEntity<Pregled> getPregledById(@PathVariable String id) {
        try {
            Pregled pregled = pregledService.getPregledById(Long.parseLong(id));
            return new ResponseEntity<>(pregled, HttpStatus.OK);
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

    @GetMapping("/osoblje/zavrseni/{id}")
    public ResponseEntity<List<Pregled>> getZavrseniPreglediByLekarId(@PathVariable String id) {
        try {

            Lekar lekar = lekarService.findLekar(id);
            List<Pregled> pregledi = pregledService.getZavrseniPreglediByLekarId(lekar.getId());

            return new ResponseEntity<>(pregledi, HttpStatus.OK);
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

    @GetMapping("/zaSestru/{id}/{datum}")
    public ResponseEntity<List<Pregled>> getSestraPregledi(@PathVariable String id, @PathVariable String datum) {
        try {
            return new ResponseEntity<List<Pregled>>(pregledService.findPreglediZaSestru(id, datum), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/zaLekara/{id}/{datum}")
    public ResponseEntity<List<Pregled>> getLekarPregledi(@PathVariable String id, @PathVariable String datum) {
        try {
            return new ResponseEntity<List<Pregled>>(pregledService.findPreglediZaLekara(id, datum), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/zaSestruOdmor/{id}/{datum1}/{datum2}")
    public ResponseEntity<List<Pregled>> getSestraPreglediOdmor(@PathVariable String id, @PathVariable String datum1, @PathVariable String datum2) {
        try {
            return new ResponseEntity<List<Pregled>>(pregledService.findPreglediZaSestruOdmor(id, datum1, datum2), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/zaLekaraOdmor/{id}/{datum1}/{datum2}")
    public ResponseEntity<List<Pregled>> getLekarPreglediOdmor(@PathVariable String id, @PathVariable String datum1, @PathVariable String datum2) {
        try {
            return new ResponseEntity<List<Pregled>>(pregledService.findPreglediZaLekaraOdmor(id, datum1, datum2), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/zakaziPregledByLekar")
    public ResponseEntity<Boolean> zakaziPregledByLekar(@RequestBody Map<String,String> body, Authentication authentication) {
        try {
            return new ResponseEntity(pregledService.zakaziPregled(Long.parseLong(body.get("pacijentKorisnikId").toString()), body.get("lekarId").toString(), body.get("datum").toString()), HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/nemajuSalu/{id}")
    public ResponseEntity<List<Pregled>> getPreglediKojiNemajuSalu(@PathVariable String id) {
        try {
            return new ResponseEntity<List<Pregled>>(pregledService.getPreglediKojiNemajuSalu(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sacuvajSalu")
    public ResponseEntity<Pregled> sacuvajSalu(@RequestBody Map<String,String> body) {
        try {

            return new ResponseEntity<Pregled>(pregledService.sacuvajPregled(body.get("pregledId").toString(), body.get("salaId").toString(), body.get("lekarId").toString(), body.get("medSestraId").toString(), body.get("termin").toString()), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
