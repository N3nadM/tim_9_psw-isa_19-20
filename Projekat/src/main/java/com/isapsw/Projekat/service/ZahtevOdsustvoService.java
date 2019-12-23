package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Klinika;
import com.isapsw.Projekat.domain.Lekar;
import com.isapsw.Projekat.domain.MedicinskaSestra;
import com.isapsw.Projekat.domain.ZahtevOdsustvo;
import com.isapsw.Projekat.dto.ZahtevOdsustvoDTO;
import com.isapsw.Projekat.repository.KlinikaRepository;
import com.isapsw.Projekat.repository.LekarRepository;
import com.isapsw.Projekat.repository.MedSestraRepository;
import com.isapsw.Projekat.repository.ZahtevOdsustvoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZahtevOdsustvoService {

    @Autowired
    private ZahtevOdsustvoRepository zahtevOdsustvoRepository;

    @Autowired
    private MedSestraRepository medSestraRepository;

    @Autowired
    private LekarRepository lekarRepository;

    @Autowired
    private KlinikaRepository klinikaRepository;

    public List<ZahtevOdsustvo> getAllZahteviOdsustvo() { return zahtevOdsustvoRepository.findAll(); }

    public ZahtevOdsustvo addZahtevOdsustvo(ZahtevOdsustvoDTO zahtevOdsustvoDTO){

        ZahtevOdsustvo zahtevOdsustvo = new ZahtevOdsustvo(zahtevOdsustvoDTO);

        MedicinskaSestra medicinskaSestra = medSestraRepository.findMedicinskaSestraByKorisnikId(Long.parseLong(zahtevOdsustvoDTO.getKorisnikId()));
        Lekar lekar = lekarRepository.findLekarByKorisnikId(Long.parseLong(zahtevOdsustvoDTO.getKorisnikId()));
        Klinika klinika = null;

        if(medicinskaSestra != null){
            zahtevOdsustvo.setMedicinskaSestra(medicinskaSestra);
            klinika = klinikaRepository.findById(medicinskaSestra.getKlinika().getId()).get();
            zahtevOdsustvo.setKlinika(klinika);
            return zahtevOdsustvoRepository.save(zahtevOdsustvo);
        }

        if(lekar != null){
            zahtevOdsustvo.setLekar(lekar);
            klinika = klinikaRepository.findById(lekar.getKlinika().getId()).get();
            zahtevOdsustvo.setKlinika(klinika);
            return zahtevOdsustvoRepository.save(zahtevOdsustvo);
        }

        return zahtevOdsustvoRepository.save(zahtevOdsustvo);
    }
}
