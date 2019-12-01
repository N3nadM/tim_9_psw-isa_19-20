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

    @PostMapping
    public ResponseEntity<Lekar> addLekar(@RequestBody LekarDTO lekar){
        try{
            return new ResponseEntity<Lekar>(lekarService.createLekar(lekar), HttpStatus.OK);
        }catch(Exception e){
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
