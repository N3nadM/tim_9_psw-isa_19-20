package com.isapsw.Projekat.controller;

import com.isapsw.Projekat.domain.Zahtev;
import com.isapsw.Projekat.service.ValidationErrorService;
import com.isapsw.Projekat.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    private ZahtevService zahtevService;

    @Autowired
    private ValidationErrorService validationErrorService;

    @PostMapping("/createRequest")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Zahtev zahtev, BindingResult result) {

        ResponseEntity<?> error = validationErrorService.validationService(result);
        if(error != null) {
            return error;
        }

        Zahtev newZahtev = zahtevService.saveZahtev(zahtev);

        return  new ResponseEntity<Zahtev>(newZahtev, HttpStatus.CREATED);
    }
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){
//        // Validate passwords match
//        userValidator.validate(user,result);
//
//        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
//        if(errorMap != null)return errorMap;
//
//        User newUser = userService.saveUser(user);
//
//        return  new ResponseEntity<User>(newUser, HttpStatus.CREATED);
//    }
}
