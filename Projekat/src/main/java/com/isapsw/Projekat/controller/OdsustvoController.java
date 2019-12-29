package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.Lek;
import com.isapsw.Projekat.domain.Lekar;
import com.isapsw.Projekat.domain.MedicinskaSestra;
import com.isapsw.Projekat.domain.Odsustvo;
import com.isapsw.Projekat.dto.OdsustvoDTO;
import com.isapsw.Projekat.repository.OdsustvoRepository;
import com.isapsw.Projekat.service.LekarService;
import com.isapsw.Projekat.service.MedSestraService;
import com.isapsw.Projekat.service.OdsustvoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/odsustvo")
public class OdsustvoController {

    @Autowired
    private OdsustvoService odsustvoService;

    @Autowired
    private MedSestraService medSestraService;

    @Autowired
    private LekarService lekarService;

    @PostMapping
    public ResponseEntity<Odsustvo> addOdsustvo(@RequestBody OdsustvoDTO odsustvoDTO){
        try {
            Odsustvo odsustvo = new Odsustvo(odsustvoDTO);

            MedicinskaSestra medicinskaSestra = medSestraService.findMedSestra(odsustvoDTO.getKorisnikId());
            Lekar lekar = lekarService.findLekar(odsustvoDTO.getKorisnikId());

            if(medicinskaSestra != null){
                odsustvo.setMedicinskaSestra(medicinskaSestra);
                medicinskaSestra.getOdsustvo().add(odsustvo);
                odsustvoService.addOdsustvo(odsustvo);
                medSestraService.updateSestra(medicinskaSestra);
            }

            if(lekar != null){
                odsustvo.setLekar(lekar);
                lekar.getOdsustvo().add(odsustvo);
                odsustvoService.addOdsustvo(odsustvo);
                lekarService.updateLekar(lekar);
            }

            return new ResponseEntity<>(odsustvo, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
