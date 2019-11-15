package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Korisnik;
import com.isapsw.Projekat.domain.Pacijent;
import com.isapsw.Projekat.domain.Zahtev;
import com.isapsw.Projekat.exceptions.UserException;
import com.isapsw.Projekat.repository.KorisnikRepository;
import com.isapsw.Projekat.repository.PacijentRepository;
import com.isapsw.Projekat.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ZahtevService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ZahtevRepository zahtevRepository;

    @Autowired
    private PacijentRepository pacijentRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    public List<Zahtev> findAll(){
        return zahtevRepository.findAll();
    }

    public void deleteById(Long id){
        zahtevRepository.deleteById(id);
    }

    public Zahtev saveZahtev(Zahtev newZahtev) {
        try {
            newZahtev.setPassword(bCryptPasswordEncoder.encode(newZahtev.getPassword()));

            Pacijent pacijent = pacijentRepository.getPacijentByJbzo(newZahtev.getJbzo());
            if(pacijent != null) {
                throw new Exception("Korisnik sa jedinstvenim zdravstvenim brojem '" + newZahtev.getJbzo() + "' vec postoji.");
            }
            Korisnik korisnik = korisnikRepository.getKorisnikByEmail(newZahtev.getEmail());
            if(korisnik != null) {
                throw new Exception("Korisnik sa emailom '" + newZahtev.getEmail() + "' vec postoji.");
            }

            return zahtevRepository.save(newZahtev);
        } catch(Exception e) {
            Zahtev zahtev = zahtevRepository.getZahtevByJbzo(newZahtev.getJbzo());
            if(zahtev != null) {
                throw new UserException("Zahtev sa jedinstvenim zdravstvenim brojem '" + newZahtev.getJbzo() + "' vec postoji.");
            }

            zahtev = zahtevRepository.getZahtevByEmail(newZahtev.getEmail());

            if(zahtev != null) {
                throw new UserException("Zahtev sa emailom '" + newZahtev.getJbzo() + "' vec postoji.");
            }

            throw new UserException(e.getMessage());
        }
    }

    @Transactional
    public void createKorisnikFromZahtev(String email) {
        try {
            Zahtev zahtev = zahtevRepository.findZahtevByEmail(email);

            Korisnik korisnik = new Korisnik(zahtev);

            Pacijent pacijent = new Pacijent(zahtev);
            pacijent.setKorisnik(korisnik);

            zahtevRepository.deleteByEmail(email);

            korisnikRepository.save(korisnik);
            pacijentRepository.save(pacijent);
        } catch (Exception e) {
            throw new UserException("Zahtev ne postoji");
        }
    }

    @Transactional
    public void denieZahtev(String email, String message) {
        try {
            zahtevRepository.deleteByEmail(email);


        } catch (Exception e) {
            throw new UserException("Zahtev ne postoji");
        }
    }
}
