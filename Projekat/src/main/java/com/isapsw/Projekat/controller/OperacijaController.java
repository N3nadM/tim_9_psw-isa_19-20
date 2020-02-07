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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/operacija")
public class OperacijaController {
    @Autowired
    private OperacijaService operacijaService;

    @Autowired
    private LekarService lekarService;

    @Autowired
    private MedSestraService medSestraService;

    @Scheduled(cron = "10 0 0 * * *")
    public void AutomatskoBiranjeSalaZaOperacije() throws ParseException, InterruptedException, MessagingException {

        operacijaService.AutomatskoBiranjeOperacije();

    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Operacija>> confirmAccount(@PathVariable String id) {
        try {
            List<Operacija> operacije = operacijaService.getOperacijeByPacijentId(Long.parseLong(id));
            return new ResponseEntity<List<Operacija>>(operacije, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/zahtev/{id}")
    public ResponseEntity<Operacija> getOperacijaById(@PathVariable String id) {
        try {
            Operacija operacija = operacijaService.getOperacijaById(Long.parseLong(id));
            return new ResponseEntity<>(operacija, HttpStatus.OK);
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

    @GetMapping("/osoblje/zavrseni/{id}")
    public ResponseEntity<List<Operacija>> getZavrseneOperacijeByLekarId(@PathVariable String id) {
        try {

            Lekar lekar = lekarService.findLekar(id);
            List<Operacija> operacije = operacijaService.getZavrseneOperacijeByLekarIdAndStanje(lekar.getId(), 2);

            return new ResponseEntity<>(operacije, HttpStatus.OK);
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

    @PostMapping("/zakaziOperacijaByLekar")
    public ResponseEntity<Boolean> zakaziOperacijaByLekar(@RequestBody Map<String,String> body, Authentication authentication) {
        try {
            return new ResponseEntity(operacijaService.zakaziOperacijuByLekar(Long.parseLong(body.get("pacijentKorisnikId").toString()), body.get("lekarId").toString(), body.get("datum").toString()), HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/nemajuSalu/{id}")
    public ResponseEntity<List<Operacija>> findOperacijeKojeNemajuSalu(@PathVariable String id) {
        try {
            return new ResponseEntity<List<Operacija>>(operacijaService.operacijeKojeNemajuSalu(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sacuvajSalu")
    public ResponseEntity<Operacija> sacuvajSalu(@RequestBody Map<String,Object> body) {
        try {
            List<Integer> lekari = new ArrayList<>((List) body.get("lekariId"));

            return new ResponseEntity<Operacija>(operacijaService.sacuvajOperaciju(body.get("operacijaId").toString(), body.get("salaId").toString(), lekari, body.get("medSestraId").toString(), body.get("termin").toString()), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
