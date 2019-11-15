package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Korisnik;
import com.isapsw.Projekat.exceptions.UserException;
import com.isapsw.Projekat.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private KorisnikRepository korisnikRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Korisnik korisnik = korisnikRepository.findByEmail(s);
        if(korisnik==null) throw new UserException("User not found");
        return korisnik;
    }
}
