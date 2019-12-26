package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.Dijagnoza;
import com.isapsw.Projekat.domain.Lek;
import com.isapsw.Projekat.domain.Lekar;
import com.isapsw.Projekat.dto.DijagnozaDTO;
import com.isapsw.Projekat.dto.LekDTO;
import com.isapsw.Projekat.service.LekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/lek")
public class LekController {

    @Autowired
    private LekService lekService;

    @GetMapping
    public List<Lek> getAllLekovi(){
        return lekService.getAllLekovi();
    }

    @GetMapping("/dijagnoza/{id}")
    public ResponseEntity<List<Lek>> confirmAcount(@PathVariable String id){
        List<Lek> lekoviZaDijagnozu = lekService.getLekoviByDijagnozaId(Long.parseLong(id));
        //List<Lek> lekoviZaDijagnozu = new ArrayList<>();

//        for(Long key : lekoviZaDijagnozuId){
//            lekoviZaDijagnozu.add(lekService.getLekById(key));
//        }
        return  new ResponseEntity<>(lekoviZaDijagnozu, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Lek> addLek(@RequestBody LekDTO lekDTO){
        try {

            Lek lek = new Lek(lekDTO);

            lekService.addLek(lek);

            return new ResponseEntity<>(lek, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
