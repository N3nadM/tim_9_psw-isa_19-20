package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.Lekar;
import com.isapsw.Projekat.domain.MedicinskaSestra;
import com.isapsw.Projekat.domain.Odmor;
import com.isapsw.Projekat.domain.Odsustvo;
import com.isapsw.Projekat.dto.OdmorDTO;
import com.isapsw.Projekat.dto.OdsustvoDTO;
import com.isapsw.Projekat.service.LekarService;
import com.isapsw.Projekat.service.MedSestraService;
import com.isapsw.Projekat.service.OdmorService;
import com.isapsw.Projekat.service.OdsustvoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/odmor")
public class OdmorController {

    @Autowired
    private OdmorService odmorService;

    @Autowired
    private MedSestraService medSestraService;

    @Autowired
    private LekarService lekarService;

    @PostMapping
    public ResponseEntity<Odmor> addOdmor(@RequestBody OdmorDTO odmorDTO){
        try {
            Odmor odmor = new Odmor(odmorDTO);

            MedicinskaSestra medicinskaSestra = medSestraService.findMedSestra(odmorDTO.getKorisnikId());
            Lekar lekar = lekarService.findLekar(odmorDTO.getKorisnikId());

            if(medicinskaSestra != null){
                odmor.setMedicinskaSestra(medicinskaSestra);
                medicinskaSestra.getOdmor().add(odmor);
                odmorService.addOdmor(odmor);
                medSestraService.updateSestra(medicinskaSestra);
            }

            if(lekar != null){
                odmor.setLekar(lekar);
                lekar.getOdmor().add(odmor);
                odmorService.addOdmor(odmor);
                lekarService.updateLekar(lekar);
            }

            return new ResponseEntity<>(odmor, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
