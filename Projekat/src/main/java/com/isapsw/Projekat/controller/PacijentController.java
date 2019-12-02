package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.PacijentDTO;
import com.isapsw.Projekat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/pacijent")
public class PacijentController {
    @Autowired
    private PacijentService pacijentService;

    @Autowired
    private KorisnikService korisnikService;

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
            System.out.println("usao");
            List<PacijentDTO> pacijenti = pacijentService.searchPacijent(body.get("ime").toString(), body.get("prezime").toString(), body.get("email").toString(), body.get("grad").toString(), body.get("jbzo").toString());
            for(PacijentDTO p : pacijenti){
                System.out.println(p.getJbzo());
            }
            return new ResponseEntity(pacijenti, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
