package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.AdminKlinike;
import com.isapsw.Projekat.domain.Authority;
import com.isapsw.Projekat.domain.Korisnik;
import com.isapsw.Projekat.dto.AdminKlinikeDTO;
import com.isapsw.Projekat.dto.KorisnikDTO;
import com.isapsw.Projekat.repository.AdminKlinikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminKlinikeService {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AdminKlinikeRepository adminKlinikeRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private KlinikaService klinikaService;

    public Optional<AdminKlinike> findById(Long id){
        return adminKlinikeRepository.findById(id);
    }

    public List<AdminKlinike> findAll(){
        return adminKlinikeRepository.findAll();
    }

    public AdminKlinike save(AdminKlinike ak){
        return adminKlinikeRepository.save(ak);
    }

    public AdminKlinike createAdminKlinike(AdminKlinikeDTO adminKlinikeDTO) {
        Korisnik k = new Korisnik(new KorisnikDTO(adminKlinikeDTO));

        k.setPassword(bCryptPasswordEncoder.encode(adminKlinikeDTO.getPassword()));

        Authority a = authorityService.findByName("ROLE_AK");
        k.getAuthorityList().add(a);

        korisnikService.addKorisnik(k);

        AdminKlinike ak = new AdminKlinike();
        ak.setKorisnik(k);
        ak.setKlinika(klinikaService.findKlinikaId(adminKlinikeDTO.getKlinikaId()).get());

        return adminKlinikeRepository.save(ak);
    }
}
