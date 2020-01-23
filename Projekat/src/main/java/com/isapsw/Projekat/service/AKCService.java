package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.AdminKlinCentra;
import com.isapsw.Projekat.domain.AdminKlinike;
import com.isapsw.Projekat.domain.Authority;
import com.isapsw.Projekat.domain.Korisnik;
import com.isapsw.Projekat.dto.AdminKCDTO;
import com.isapsw.Projekat.dto.AdminKlinikeDTO;
import com.isapsw.Projekat.dto.KorisnikDTO;
import com.isapsw.Projekat.repository.AKCRepository;
import com.isapsw.Projekat.repository.AdminKlinikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AKCService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private AKCRepository akcRepository;

    public AdminKlinCentra createAdminKC(AdminKCDTO adminKCDTO) {
        Korisnik k = new Korisnik(new KorisnikDTO(adminKCDTO));

        k.setPassword(bCryptPasswordEncoder.encode(adminKCDTO.getPassword()));

        Authority a = authorityService.findByName("ROLE_AKC");
        k.getAuthorityList().add(a);

        korisnikService.addKorisnik(k);

        AdminKlinCentra akc = new AdminKlinCentra();
        akc.setKorisnik(k);

        return akcRepository.save(akc);
    }

    public AdminKlinCentra getAdminByKorisnikId(String id){
        return akcRepository.findAdminKlinCentraByKorisnikId(Long.parseLong(id));
    }
}
