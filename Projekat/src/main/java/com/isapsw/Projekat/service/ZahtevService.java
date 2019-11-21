package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.exceptions.UserException;
import com.isapsw.Projekat.repository.*;
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

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private ZdrKartonRepository zdrKartonRepository;

    public List<Zahtev> findAll(){
        return zahtevRepository.findAll();
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

            if(!zahtev.isVerified()) {
                throw new Exception("Zahtev nije verifikovan od strane Administratora klinickog centra");
            }

            Authority authority = authorityRepository.findAuthorityByName("ROLE_PACIJENT");

            Korisnik korisnik = new Korisnik(zahtev);
            korisnik.getAuthorityList().add(authority);

            Pacijent pacijent = new Pacijent(zahtev);
            pacijent.setKorisnik(korisnik);

            korisnikRepository.save(korisnik);
            pacijentRepository.save(pacijent);

            ZdrKarton zdrKarton = new ZdrKarton();
            zdrKarton.setPacijent(pacijent);

            zdrKartonRepository.save(zdrKarton);
            zahtevRepository.deleteByEmail(email);
        } catch (Exception e) {
            throw new UserException(e.getMessage());
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
