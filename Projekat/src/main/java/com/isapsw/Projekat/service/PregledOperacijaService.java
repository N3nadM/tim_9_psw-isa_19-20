package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PregledOperacijaService {

    @Autowired
    private ZdrKartonRepository zdrKartonRepository;

    @Autowired
    private DijagnozaRepository dijagnozaRepository;

    @Autowired
    private LekRepository lekRepository;

    @Autowired
    private MedSestraRepository medSestraRepository;

    @Autowired
    private ReceptRepository receptRepository;

    public String zavrsiPregled(Map<String,Object> body){

        for(String s : body.keySet()){
            System.out.println(body.get(s).toString());
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Date date = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = format1.format(date);
        Date inActiveDate = null;
        try {
            inActiveDate = format1.parse(date1);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }


        ZdrKarton zdrKarton = zdrKartonRepository.findById(Long.parseLong(body.get("id").toString())).get();
        MedicinskaSestra medicinskaSestra = medSestraRepository.findById(Long.parseLong(body.get("medSestraId").toString())).get();

        Dijagnoza dijagnoza = dijagnozaRepository.findDijagnozaById(Long.parseLong(body.get("dijagnoza").toString()));

        zdrKarton.getIstorijaBolesti().add(dijagnoza);
        dijagnoza.getZdrKarton().add(zdrKarton);

        List<String> lekoviString = new ArrayList<>((List) body.get("lekovi"));
        for(String naziv : lekoviString) {
            Lek lek = lekRepository.findLekByNaziv(naziv);
            lek.getZdrKarton().add(zdrKarton);

            Recept recept = new Recept();
            recept.setLek(lek);
            recept.setMedicinskaSestra(medicinskaSestra);
            recept.setDatumIsticanja(inActiveDate);
            recept.getZdrKarton().add(zdrKarton);
            receptRepository.save(recept);

            zdrKarton.getIzdatiRecepti().add(recept);
            medicinskaSestra.getRecepti().add(recept);
        }



        return "zavrseno";
    }
}
