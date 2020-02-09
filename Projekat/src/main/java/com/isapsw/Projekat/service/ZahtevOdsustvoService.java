package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.ZahtevOdsustvoDTO;
import com.isapsw.Projekat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
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

    @Autowired
    private EmailService emailService;

    @Autowired
    private OdsustvoRepository odsustvoRepository;

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

    public List<ZahtevOdsustvo> getZahteviNaKlinici(String id){
        return zahtevOdsustvoRepository.findAllByKlinikaId(Long.parseLong(id));
    }

    @Transactional
    public ZahtevOdsustvo denyZahtev(String id, String message) throws MessagingException, InterruptedException {
        ZahtevOdsustvo zahtevOdsustvo = zahtevOdsustvoRepository.findByIdTransaction(Long.parseLong(id)).get();
        String email = "";
        if(zahtevOdsustvo.getLekar() == null){
            email = zahtevOdsustvo.getMedicinskaSestra().getKorisnik().getEmail();
        }else {
            email = zahtevOdsustvo.getLekar().getKorisnik().getEmail();
        }
        emailService.sendOdsustvoOdbijanje(email, message);
        zahtevOdsustvoRepository.delete(zahtevOdsustvo);
        return zahtevOdsustvo;
    }

    @Transactional
    public ZahtevOdsustvo acceptZahtev(String id) throws MessagingException, InterruptedException {
        ZahtevOdsustvo zahtevOdsustvo = zahtevOdsustvoRepository.findByIdTransaction(Long.parseLong(id)).get();
        String email = "";
        Odsustvo odsustvo = new Odsustvo();
        odsustvo.setDatum(zahtevOdsustvo.getDatum());
        odsustvo.setOpis(zahtevOdsustvo.getOpis());
        if(zahtevOdsustvo.getLekar() == null){
            email = zahtevOdsustvo.getMedicinskaSestra().getKorisnik().getEmail();
            odsustvo.setMedicinskaSestra(zahtevOdsustvo.getMedicinskaSestra());
        }else{
            email = zahtevOdsustvo.getLekar().getKorisnik().getEmail();
            odsustvo.setLekar(zahtevOdsustvo.getLekar());
        }
        odsustvoRepository.save(odsustvo);
        String message="Vas zahtev za odsustvo datuma " + zahtevOdsustvo.getDatum() + " je prihvacen.";
        emailService.sendOdsustvoPrihvatanje(email, message);
        zahtevOdsustvoRepository.delete(zahtevOdsustvo);
        return zahtevOdsustvo;
    }
}
