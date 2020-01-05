package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Autowired
    private PregledRepository pregledRepository;

    @Autowired
    private OperacijaRepository operacijaRepository;

    public Object izmeniIzvestaj(@RequestBody Map<String,Object> body){

            int vrsta = (int) body.get("vrsta");

            if(vrsta == 0){
                Pregled pregled = pregledRepository.findById(Long.parseLong(body.get("id").toString())).get();

                pregled.setIzvestaj(body.get("izvestaj").toString());
                pregledRepository.save(pregled);
                return pregled;
            }else if(vrsta == 1){
                Operacija operacija = operacijaRepository.findById(Long.parseLong(body.get("id").toString())).get();

                operacija.setIzvestaj(body.get("izvestaj").toString());
                operacijaRepository.save(operacija);
                return operacija;
            }

            return null;
    }

    public Object zapocniPregledOperaciju(Map<String,Object> body){
        int vrsta = (int) body.get("vrsta");

        if(vrsta == 0){
            Pregled pregled = pregledRepository.findById(Long.parseLong(body.get("idPregledOperacija").toString())).get();

            pregled.setStanje(1);
            pregledRepository.save(pregled);
            return pregled;
        }else if(vrsta == 1){
            Operacija operacija = operacijaRepository.findById(Long.parseLong(body.get("idPregledOperacija").toString())).get();

            operacija.setStanje(1);
            operacijaRepository.save(operacija);
            return operacija;
        }

        return null;
    }

    public Object zavrsiPregledOperaciju(Map<String,Object> body){

        Object ret = null;

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

        int vrsta = (int) body.get("vrsta");

        if(vrsta == 0){
            Pregled pregled = pregledRepository.findById(Long.parseLong(body.get("idPregledOperacija").toString())).get();
            pregled.setIzvestaj(body.get("izvestaj").toString());
            pregled.setStanje(2);
            pregledRepository.save(pregled);
            ret = pregled;
        }else if(vrsta == 1){
            Operacija operacija = operacijaRepository.findById(Long.parseLong(body.get("idPregledOperacija").toString())).get();
            operacija.setIzvestaj(body.get("izvestaj").toString());
            operacija.setStanje(2);
            operacijaRepository.save(operacija);
            ret = operacija;
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
            recept.setZdrKarton(zdrKarton);
            receptRepository.save(recept);

            zdrKarton.getIzdatiRecepti().add(recept);
            medicinskaSestra.getRecepti().add(recept);
        }



        return ret;
    }
}
