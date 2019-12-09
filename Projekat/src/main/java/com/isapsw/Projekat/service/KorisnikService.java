package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Korisnik;
import com.isapsw.Projekat.dto.KorisnikDTO;
import com.isapsw.Projekat.exceptions.UserException;
import com.isapsw.Projekat.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KorisnikService {
    @Autowired
    private KorisnikRepository korisnikRepository;

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

    public Korisnik findByUsername(String username) {
        return korisnikRepository.findByEmail(username);
    }
}
