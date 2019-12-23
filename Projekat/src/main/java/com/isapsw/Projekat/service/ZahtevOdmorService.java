package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.ZahtevOdmorDTO;
import com.isapsw.Projekat.dto.ZahtevOdsustvoDTO;
import com.isapsw.Projekat.repository.KlinikaRepository;
import com.isapsw.Projekat.repository.LekarRepository;
import com.isapsw.Projekat.repository.MedSestraRepository;
import com.isapsw.Projekat.repository.ZahtevOdmorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZahtevOdmorService {

    @Autowired
    private ZahtevOdmorRepository zahtevOdmorRepository;

    @Autowired
    private MedSestraRepository medSestraRepository;

    @Autowired
    private LekarRepository lekarRepository;

    @Autowired
    private KlinikaRepository klinikaRepository;

    public List<ZahtevOdmor> getAllZahteviOdmor(){ return zahtevOdmorRepository.findAll(); }

    public ZahtevOdmor addZahtevOdmor(ZahtevOdmorDTO zahtevOdmorDTO){

        ZahtevOdmor zahtevOdmor = new ZahtevOdmor(zahtevOdmorDTO);

        MedicinskaSestra medicinskaSestra = medSestraRepository.findMedicinskaSestraByKorisnikId(Long.parseLong(zahtevOdmorDTO.getKorisnikId()));
        Lekar lekar = lekarRepository.findLekarByKorisnikId(Long.parseLong(zahtevOdmorDTO.getKorisnikId()));
        Klinika klinika = null;

        if(medicinskaSestra != null){
            zahtevOdmor.setMedicinskaSestra(medicinskaSestra);
            klinika = klinikaRepository.findById(medicinskaSestra.getKlinika().getId()).get();
            zahtevOdmor.setKlinika(klinika);
            return zahtevOdmorRepository.save(zahtevOdmor);
        }

        if(lekar != null){
            zahtevOdmor.setLekar(lekar);
            klinika = klinikaRepository.findById(lekar.getKlinika().getId()).get();
            zahtevOdmor.setKlinika(klinika);
            return zahtevOdmorRepository.save(zahtevOdmor);
        }

        return zahtevOdmorRepository.save(zahtevOdmor);
    }

    public List<ZahtevOdmor> getZahteviNaKlinici(String id){
        return zahtevOdmorRepository.findAllByKlinikaId(Long.parseLong(id));
    }
}
