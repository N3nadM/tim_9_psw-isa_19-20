package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.ZahtevOdmorDTO;
import com.isapsw.Projekat.dto.ZahtevOdsustvoDTO;
import com.isapsw.Projekat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
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

    @Autowired
    private EmailService emailService;

    @Autowired
    private OdmorRepository odmorRepository;

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

    public ZahtevOdmor denyZahtev(String id, String message) throws MessagingException, InterruptedException {
        ZahtevOdmor zahtevOdmor = zahtevOdmorRepository.findById(Long.parseLong(id)).get();
        String email = "";
        if(zahtevOdmor.getLekar() == null){
            email = zahtevOdmor.getMedicinskaSestra().getKorisnik().getEmail();
        }else {
            email = zahtevOdmor.getLekar().getKorisnik().getEmail();
        }
        emailService.sendOdmorOdbijanje(email, message);
        zahtevOdmorRepository.delete(zahtevOdmor);
        return zahtevOdmor;
    }

    public ZahtevOdmor acceptZahtev(String id) throws MessagingException, InterruptedException {
        ZahtevOdmor zahtevOdmor = zahtevOdmorRepository.findById(Long.parseLong(id)).get();
        String email = "";
        Odmor odmor = new Odmor();
        odmor.setDatumOd(zahtevOdmor.getDatumOd());
        odmor.setDatumDo(zahtevOdmor.getDatumDo());
        odmor.setOpis(zahtevOdmor.getOpis());
        odmorRepository.save(odmor);
        if(zahtevOdmor.getLekar() == null){
            email = zahtevOdmor.getMedicinskaSestra().getKorisnik().getEmail();
            odmor.setMedicinskaSestra(zahtevOdmor.getMedicinskaSestra());
        }else{
            email = zahtevOdmor.getLekar().getKorisnik().getEmail();
            odmor.setLekar(zahtevOdmor.getLekar());
        }

        String message="Vas zahtev za godisnji odmor od datuma " + zahtevOdmor.getDatumOd() + " do datuma "+ zahtevOdmor.getDatumDo()+ "je prihvacen.";
        emailService.sendOdmorPrihvatanje(email, message);
        zahtevOdmorRepository.delete(zahtevOdmor);
        return zahtevOdmor;
    }
}
