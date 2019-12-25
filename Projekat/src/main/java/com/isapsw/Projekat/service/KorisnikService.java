package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Korisnik;
import com.isapsw.Projekat.dto.KorisnikDTO;
import com.isapsw.Projekat.exceptions.UserException;
import com.isapsw.Projekat.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class KorisnikService {
    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Korisnik editKorisnik(KorisnikDTO korisnik) {

            Korisnik k = korisnikRepository.findKorisnikById(korisnik.getId());
            if(k == null) {
                return k;
            }
            k.setAdresa(korisnik.getAdresa());
            k.setGrad(korisnik.getGrad());
            k.setDrzava(korisnik.getDrzava());
            k.setIme(korisnik.getIme());
            k.setPrezime(korisnik.getPrezime());
            k.setTelefon(korisnik.getTelefon());

            korisnikRepository.save(k);

            return k;

    }

    public Korisnik addKorisnik(Korisnik k){
        return korisnikRepository.save(k);
    }

    public Korisnik findKoriskikId(Long id) { return korisnikRepository.findKorisnikById(id); }

    public Boolean changePassword(Korisnik korisnik, String newPassword, String oldPassword) {

        if(!bCryptPasswordEncoder.matches(oldPassword, korisnik.getPassword())) {
            return false;
        }

        korisnik.setPassword(bCryptPasswordEncoder.encode(newPassword));
        korisnik.setDateModified(new Date());

        korisnikRepository.save(korisnik);

        return true;
    }

    public Korisnik findByUsername(String username) {
        return korisnikRepository.findByEmail(username);
    }
}
