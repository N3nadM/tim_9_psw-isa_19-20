package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Korisnik;
import com.isapsw.Projekat.domain.Pacijent;
import com.isapsw.Projekat.domain.ZdrKarton;
import com.isapsw.Projekat.dto.PacijentDTO;
import com.isapsw.Projekat.repository.KorisnikRepository;
import com.isapsw.Projekat.repository.PacijentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacijentService {

    @Autowired
    private PacijentRepository pacijentRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    public Pacijent savePacijent(Pacijent pacijent) {
        return pacijentRepository.save(pacijent);
    }

    public Pacijent findPacijent(String id) {
        return pacijentRepository.findPacijentByKorisnikId(Long.parseLong(id));
    }

    public List<PacijentDTO> searchPacijent(List<PacijentDTO> pacijentiKl, String ime, String prezime, String email, String grad, String jbzo){

        List<Long> pacijentId = pacijentRepository.findPacijentByParameters(ime, prezime, email, grad, jbzo);

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
}
