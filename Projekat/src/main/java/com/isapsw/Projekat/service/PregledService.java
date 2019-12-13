package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.Lekar;
import com.isapsw.Projekat.domain.Pregled;
import com.isapsw.Projekat.domain.Sala;
import com.isapsw.Projekat.domain.TipPregleda;
import com.isapsw.Projekat.dto.PregledDTO;
import com.isapsw.Projekat.repository.LekarRepository;
import com.isapsw.Projekat.repository.PregledRepository;
import com.isapsw.Projekat.repository.SalaRepository;
import com.isapsw.Projekat.repository.TipoviPregledaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PregledService {

    @Autowired
    private PregledRepository pregledRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private LekarRepository lekarRepository;

    @Autowired
    private TipoviPregledaRepository tipoviPregledaRepository;

    public List<Pregled> getPreglediByPacijentId(Long id) {
        return pregledRepository.findPregledByPacijentId(id);
    }

    public List<Pregled> getPreglediByLekarId(Long id) {
        return pregledRepository.findPregledByLekarId(id);
    }

    public List<Pregled> getPreglediByMedSestraId(Long id){ return pregledRepository.findPregledsByMedicinskaSestraId(id); }

    public List<Pregled> getPreglediBySalaId(Long id) { return pregledRepository.findPregledBySalaId(id); }

    public Pregled addPregled(PregledDTO pregledDTO) throws ParseException {
        Pregled pregled = new Pregled();
        Optional<Sala> sala = salaRepository.findById(Long.parseLong(pregledDTO.getSalaId()));
        pregled.setSala(sala.get());
        Lekar lekar = lekarRepository.findLekarById(Long.parseLong(pregledDTO.getLekarId()));
        pregled.setLekar(lekar);

        Optional<TipPregleda> tp = tipoviPregledaRepository.findById(Long.parseLong(pregledDTO.getTipPregledaId()));
        pregled.setTipPregleda(tp.get());

        pregled.setMedicinskaSestra(null);
        pregled.setPacijent(null);

        SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        Date datum = sdf.parse(pregledDTO.getDatum());
        Date d2 = sdf.parse(pregledDTO.getDatum());
        pregled.setDatumPocetka(datum);

        d2.setTime(d2.getTime() + tp.get().getMinimalnoTrajanjeMin() * 600 * 1000);
        pregled.setDatumZavrsetka(d2);

        Date date = Calendar.getInstance().getTime();
        pregled.setDatumKreiranja(date);
        pregled.setPopust(Integer.parseInt(pregledDTO.getPopust()));

        pregled.setIzvestaj("");
        return pregledRepository.save(pregled);
    }
}
