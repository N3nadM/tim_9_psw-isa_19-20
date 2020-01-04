package com.isapsw.Projekat.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.PacijentDTO;
import com.isapsw.Projekat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/pacijent")
public class PacijentController {
    @Autowired
    private PacijentService pacijentService;

    @Autowired
    private MedSestraService medSestraService;

    @Autowired
    private LekarService lekarService;

    @Autowired
    private KlinikaService klinikaService;

    @GetMapping("/{id}")
    public ResponseEntity<Pacijent> confirmAccount(@PathVariable String id) {

        Pacijent pacijent = pacijentService.findPacijent(id);
        return new ResponseEntity<Pacijent>(pacijent, HttpStatus.OK);
    }

    @GetMapping("/pacijentiKlinike/{id}")
    public ResponseEntity<List<PacijentDTO>> getPacijentiKlinike(@PathVariable String id){

        try{
            MedicinskaSestra medSestra = medSestraService.findMedSestra(id);
            Lekar lekar = lekarService.findLekar(id);

            Optional<Klinika> klinika;
            List<Pacijent> pacijenti = new ArrayList<>();
            List<PacijentDTO> pacijentDTOList = new ArrayList<>();

            if(medSestra == null && lekar == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
            else if(lekar == null){
                klinika = klinikaService.findKlinikaId(medSestra.getKlinika().getId());
                pacijenti = klinika.get().getPacijenti();

            }else if(medSestra == null){
                klinika = klinikaService.findKlinikaId(lekar.getKlinika().getId());
                pacijenti = klinika.get().getPacijenti();
            }

            for (Pacijent pacijent : pacijenti) {
                PacijentDTO pacijentDTO = new PacijentDTO(pacijent.getKorisnik());
                pacijentDTO.setJbzo(pacijent.getJbzo());
                pacijentDTOList.add(pacijentDTO);
            }

            return new ResponseEntity<List<PacijentDTO>>(pacijentDTOList, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<Pacijent>> getPacijentiWithSearch(@RequestBody Map<String,String> body) {
        try {
            List<PacijentDTO> pacijentiKlinike = getPacijentiKlinike(body.get("korisnikId")).getBody();
            List<PacijentDTO> pacijenti = pacijentService.searchPacijent(pacijentiKlinike, body.get("ime").toString(), body.get("prezime").toString(), body.get("jbzo").toString());

            return new ResponseEntity(pacijenti, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getZdrKarton/{id}")
    public ResponseEntity<ZdrKarton> getZdrKarton(@PathVariable String id){

        Pacijent pacijent = pacijentService.findPacijentById(id);
        ZdrKarton zdrKarton = pacijent.getZdrKarton();
        try{
            return new ResponseEntity<ZdrKarton>(zdrKarton, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/proveraPregledan/{idLekar}/{idPacijent}")
    public ResponseEntity<Pacijent> getZdrKarton(@PathVariable String idLekar, @PathVariable String idPacijent){

        Pacijent pacijent = pacijentService.proveraPregledOperacija(idLekar, idPacijent);
        try{
            return new ResponseEntity<Pacijent>(pacijent, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
