package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.Korisnik;
import com.isapsw.Projekat.domain.Zahtev;
import com.isapsw.Projekat.payload.LoginRequest;
import com.isapsw.Projekat.security.JWTTokenProvider;
import com.isapsw.Projekat.service.EmailService;
import com.isapsw.Projekat.service.ValidationErrorService;
import com.isapsw.Projekat.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.isapsw.Projekat.security.Konstante.TOKEN_BEARER_PREFIX;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private ZahtevService zahtevService;

    @Autowired
    private ValidationErrorService validationErrorService;

    @Autowired
    private EmailService emailService;


    @Autowired
    private JWTTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> error = validationErrorService.validationService(result);
        if(error != null) return error;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_BEARER_PREFIX +  tokenProvider.generate(authentication);

        return ResponseEntity.ok(jwt);
    }

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
        System.out.println("Tu samm");
        try {
            emailService.sendConfirmationAsync(email);
        } catch(NullPointerException e) {
            System.out.println("Zahtev ne postoji");
        }
        catch (Exception e) {
            System.out.println("Email nije poslat");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/confirm/{email}")
    public ResponseEntity<String> confirmAccount(@PathVariable String email) {

        zahtevService.createKorisnikFromZahtev(email);

        return new ResponseEntity<String>("Korisnik kreiran", HttpStatus.OK);
    }

    @PostMapping("/denie/{email}")
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
