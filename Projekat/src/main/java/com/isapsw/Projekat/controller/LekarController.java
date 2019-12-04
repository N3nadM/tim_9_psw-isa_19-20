package com.isapsw.Projekat.controller;
import com.isapsw.Projekat.domain.Lek;
import com.isapsw.Projekat.domain.Lekar;
import com.isapsw.Projekat.dto.LekarDTO;
import com.isapsw.Projekat.service.LekarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @PostMapping("/getTermini")
    public ResponseEntity<List<String>> getSlobodniTermini(@RequestBody Map<String,Object> body){
        try {
            List<String> termini = lekarService.findSlobodniTermini(Long.parseLong(body.get("id").toString()), body.get("datum").toString());
            return new ResponseEntity<>(termini, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Lekar> addLekar(@RequestBody LekarDTO lekar){
        try{
            return new ResponseEntity<Lekar>(lekarService.createLekar(lekar), HttpStatus.OK);
        }catch(Exception e){
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
