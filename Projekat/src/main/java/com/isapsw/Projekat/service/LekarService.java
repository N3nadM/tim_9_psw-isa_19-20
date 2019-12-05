package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.KorisnikDTO;
import com.isapsw.Projekat.dto.LekarDTO;
import com.isapsw.Projekat.repository.LekarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

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

    public List<String> findSlobodniTermini(Long id, String datum) {
        Lekar lekar = lekarRepository.findLekarById(id);

        List<String> termini = new ArrayList<>();

        lekar.getPregledi().sort((p, k) -> p.getDatumPocetka().after(k.getDatumPocetka()) ? 1 : -1);

        List<Pregled> preglediIstogDanaJednogLekara = new ArrayList<>();

        for(int i = 0; i < lekar.getPregledi().size(); i++) {
            if (KlinikaService.compareDatesOnly(Date.from(Instant.parse(datum)), lekar.getPregledi().get(i).getDatumPocetka())) {
                preglediIstogDanaJednogLekara.add(lekar.getPregledi().get(i));
            }
        }

        Date pocetak = KlinikaService.makeDateFromDateAndTime(Date.from(Instant.parse(datum)),lekar.getPocetakRadnogVremena());
        Date kraj = KlinikaService.makeDateFromDateAndTime(Date.from(Instant.parse(datum)),lekar.getKrajRadnogVremena());

        if(preglediIstogDanaJednogLekara.size() == 0) {
            while(pocetak.getTime() < kraj.getTime()) {
                termini.add(pocetak.toString());
                pocetak.setTime(pocetak.getTime() + lekar.getTipPregleda().getMinimalnoTrajanjeMin() * 60 * 1000);
            }
        } else {
            for(int i = 0; i < preglediIstogDanaJednogLekara.size(); i++) {
                while(pocetak.getTime() < kraj.getTime() && pocetak.getTime() + lekar.getTipPregleda().getMinimalnoTrajanjeMin() * 60 * 1000 < preglediIstogDanaJednogLekara.get(i).getDatumPocetka().getTime()) {
                    termini.add(pocetak.toString());
                    pocetak.setTime(pocetak.getTime() + lekar.getTipPregleda().getMinimalnoTrajanjeMin() * 60 * 1000);
                 }
                pocetak.setTime(preglediIstogDanaJednogLekara.get(i).getDatumZavrsetka().getTime());
                if(i == preglediIstogDanaJednogLekara.size() - 1) {
                    while(pocetak.getTime() < kraj.getTime()) {
                        termini.add(pocetak.toString());
                        pocetak.setTime(pocetak.getTime() + lekar.getTipPregleda().getMinimalnoTrajanjeMin() * 60 * 1000);
                    }
                }
            }
        }

        return new ArrayList<>(termini);
    }

    public Lekar createLekar(LekarDTO lekarDTO) {
        Korisnik k = new Korisnik(new KorisnikDTO(lekarDTO));

        k.setPassword(bCryptPasswordEncoder.encode(lekarDTO.getPassword()));
        Authority a = authorityService.findByName("ROLE_LEKAR");
        k.getAuthorityList().add(a);
        korisnikService.addKorisnik(k);

        Lekar l = new Lekar();
        l.setKorisnik(k);
        l.setPocetakRadnogVremena(LocalTime.parse(lekarDTO.getPocetakRadnogVremena()));
        l.setKrajRadnogVremena(LocalTime.parse(lekarDTO.getKrajRadnogVremena()));
        l.setKlinika(klinikaService.findKlinikaId(lekarDTO.getKlinikaId()).get());
        l.setTipPregleda(tipPregledaService.getTipPregledaById(lekarDTO.getTipPregledaId()).get());
        return lekarRepository.save(l);

    }

    public Lekar editLekarByAdmin(String id,LekarDTO lekarDTO) throws Exception {
        Lekar l = lekarRepository.findLekarByKorisnikId(Long.parseLong(id));
        Korisnik k = korisnikService.findKoriskikId(Long.parseLong(id));
        k.setIme(lekarDTO.getIme());
        k.setPrezime(lekarDTO.getPrezime());
        k.setAdresa(lekarDTO.getAdresa());
        k.setGrad(lekarDTO.getGrad());
        k.setDrzava(lekarDTO.getDrzava());
        k.setTelefon(lekarDTO.getTelefon());
        l.setKorisnik(k);

        l.getPregledi().sort((a,b) -> a.getDatumPocetka().after(b.getDatumPocetka()) ? 1 : -1);

        Date datumSada = new Date();


        LocalTime noviPocetak = LocalTime.parse(lekarDTO.getPocetakRadnogVremena());
        LocalTime noviKraj = LocalTime.parse(lekarDTO.getKrajRadnogVremena());

        for(int i = 0; i < l.getPregledi().size(); i++) {

            if(l.getPregledi().get(i).getDatumPocetka().after(datumSada)) {
                LocalTime pocetakRadnogVremena = l.getPocetakRadnogVremena();
                LocalTime krajRadnogVremena = l.getKrajRadnogVremena();


                if (LocalDateTime.ofInstant(l.getPregledi().get(i).getDatumPocetka().toInstant(), ZoneId.systemDefault()).toLocalTime().compareTo(noviPocetak) == -1) {
                    throw new Exception("Lekar ima preglede");
                }
                if(LocalDateTime.ofInstant(l.getPregledi().get(i).getDatumZavrsetka().toInstant(), ZoneId.systemDefault()).toLocalTime().compareTo(noviKraj) == 1) {
                    throw new Exception("Lekar ima preglede");
                }
            }
        }

        l.setPocetakRadnogVremena(noviPocetak);
        l.setKrajRadnogVremena(noviKraj);

        return lekarRepository.save(l);
    }
}
