package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Authority;
import com.isapsw.Projekat.domain.Korisnik;
import com.isapsw.Projekat.domain.Lekar;
import com.isapsw.Projekat.dto.KorisnikDTO;
import com.isapsw.Projekat.dto.LekarDTO;
import com.isapsw.Projekat.repository.LekarRepository;
import com.isapsw.Projekat.repository.TipPregledaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LekarService {

    @Autowired
    private LekarRepository lekarRepository;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private KlinikaService klinikaService;

    @Autowired
    private TipPregledaService tipPregledaService;

    public Lekar findLekar(String id) {
        return lekarRepository.findLekarByKorisnikId(Long.parseLong(id));
    }

    public List<Date> findSlobodniTermini(Long id) {
        List<Date> termini = new ArrayList<>();

        termini.add(new Date());

        return termini;
    }

    public Lekar createLekar(LekarDTO lekarDTO) {
        Korisnik k = new Korisnik(new KorisnikDTO(lekarDTO));

        k.setPassword(bCryptPasswordEncoder.encode(lekarDTO.getPassword()));
        Authority a = authorityService.findByName("ROLE_LEKAR");
        k.getAuthorityList().add(a);
        korisnikService.addKorisnik(k);

        Lekar l = new Lekar();
        l.setKorisnik(k);
        l.setKlinika(klinikaService.findKlinikaId(lekarDTO.getKlinikaId()).get());
        l.setTipPregleda(tipPregledaService.getTipPregledaById(lekarDTO.getTipPregledaId()).get());
        return lekarRepository.save(l);

    }
}
