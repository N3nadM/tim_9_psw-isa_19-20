package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.Korisnik;
import com.isapsw.Projekat.domain.Zahtev;
import com.isapsw.Projekat.service.EmailService;
import com.isapsw.Projekat.service.ValidationErrorService;
import com.isapsw.Projekat.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private ZahtevService zahtevService;

    @Autowired
    private ValidationErrorService validationErrorService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/createRequest")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Zahtev zahtev, BindingResult result) {
        ResponseEntity<?> error = validationErrorService.validationService(result);
        if(error != null) {
            return error;
        }

        Zahtev newZahtev = zahtevService.saveZahtev(zahtev);

        return  new ResponseEntity<Zahtev>(newZahtev, HttpStatus.CREATED);
    }

    @PostMapping("/register/{email}")
    public ResponseEntity<?> registerUser(@PathVariable String email) {
        try {
            emailService.sendConfirmationAsync(email);
        } catch (Exception e) {
            System.out.println("Email nije poslat");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/confirm/{email}")
    public ResponseEntity<String> confirmAccount(@PathVariable String email) {

        zahtevService.createKorisnikFromZahtev(email);

        return new ResponseEntity<String>("Korisnik kreiran", HttpStatus.OK);
    }

    @GetMapping("/denie/{email}")
    public ResponseEntity<String> denieAccount(@PathVariable String email, @RequestBody String message) {

        zahtevService.denieZahtev(email, message);

        try {
            emailService.sendDeniedAsync(email, message);
        } catch (Exception e) {
            System.out.println("Email nije poslat");
        }

        return new ResponseEntity<String>("Korisnik Odbijen", HttpStatus.OK);
    }
}
