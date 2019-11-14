package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Pacijent;
import com.isapsw.Projekat.domain.Zahtev;
import com.isapsw.Projekat.exceptions.UserAlreadyExistsException;
import com.isapsw.Projekat.repository.PacijentRepository;
import com.isapsw.Projekat.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZahtevService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ZahtevRepository zahtevRepository;

    @Autowired
    private PacijentRepository pacijentRepository;

    public List<Zahtev> findAll(){
        return zahtevRepository.findAll();
    }

    public void deleteById(Long id){
        zahtevRepository.deleteById(id);
    }

    public Zahtev saveZahtev(Zahtev newZahtev) {
        try {
            newZahtev.setPassword(bCryptPasswordEncoder.encode(newZahtev.getPassword()));

            return zahtevRepository.save(newZahtev);
        } catch(Exception e) {
            Zahtev zahtev = zahtevRepository.getZahtevByJbzo(newZahtev.getJbzo());
            if(zahtev != null) {
                throw new UserAlreadyExistsException("Korisnik sa jedinstvenim zdravstvenim brojem '" + newZahtev.getJbzo() + "' vec postoji.");
            }

            Pacijent pacijent = pacijentRepository.getPacijentByJbzo(newZahtev.getJbzo());
            if(pacijent != null) {
                throw new UserAlreadyExistsException("Korisnik sa jedinstvenim zdravstvenim brojem '" + newZahtev.getJbzo() + "' vec postoji.");
            }

            throw new UserAlreadyExistsException("Korisnik sa emailom '" + newZahtev.getEmail() + "' vec postoji.");
        }
    }
}
