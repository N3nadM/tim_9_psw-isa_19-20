package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Korisnik;
import com.isapsw.Projekat.domain.Pacijent;
import com.isapsw.Projekat.dto.PacijentDTO;
import com.isapsw.Projekat.repository.KorisnikRepository;
import com.isapsw.Projekat.repository.PacijentRepository;
import com.isapsw.Projekat.repository.PregledRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PacijentService {

    @Autowired
    private PacijentRepository pacijentRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private PregledRepository pregledRepository;

    public Pacijent savePacijent(Pacijent pacijent) {
        return pacijentRepository.save(pacijent);
    }

    public Pacijent findPacijent(String id) {
        return pacijentRepository.findPacijentByKorisnikId(Long.parseLong(id));
    }

    public Pacijent findPacijentById(String id) {return pacijentRepository.findPacijentById(Long.parseLong(id)); }

    public Pacijent findPacijentByZdrKartonId(String id) { return pacijentRepository.findPacijentByZdrKartonId(Long.parseLong(id));}

    public List<PacijentDTO> searchPacijent(List<PacijentDTO> pacijentiKl, String ime, String prezime, String jbzo){

        List<Long> pacijentId = pacijentRepository.findPacijentByParameters(ime.toUpperCase(), prezime.toUpperCase(), jbzo.toUpperCase());

        List<PacijentDTO> pacijenti = new ArrayList<>();

        for(Long id : pacijentId){

            PacijentDTO pacijentDTO = new PacijentDTO(korisnikRepository.findKorisnikById(id));
            Pacijent pacijent = pacijentRepository.findPacijentByKorisnikId(id);
            pacijentDTO.setJbzo(pacijent.getJbzo());
            for(PacijentDTO pDTO : pacijentiKl){
                if(pDTO.getJbzo() == pacijentDTO.getJbzo()){
                    pacijenti.add(pacijentDTO);
                    break;
                }
            }
        }

        return pacijenti;
    }

    public Pacijent proveraPregledOperacija(String idKorisnik, String idPacijent){
        Date date = Calendar.getInstance().getTime();
        Korisnik k = korisnikRepository.findKorisnikById(Long.parseLong(idKorisnik));
        List<Pacijent> pac = new ArrayList<>();
        if(k.getAuthorityList().get(0).getId().equals(Long.parseLong("2"))){
            pac = pregledRepository.proveraPregled(Long.parseLong(idKorisnik), Long.parseLong(idPacijent), date);
        }else if(k.getAuthorityList().get(0).getId().equals(Long.parseLong("5"))){
            pac = pregledRepository.proveraPregledSestra(Long.parseLong(idKorisnik), Long.parseLong(idPacijent), date);
        }
        if(pac.isEmpty() || pac == null){
            return null;
        }else{
            return pac.get(0);
        }
    }
}
