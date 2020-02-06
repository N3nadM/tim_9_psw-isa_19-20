package com.isapsw.Projekat.Rezervacije.service;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.KlinikaDTO;
import com.isapsw.Projekat.dto.LekarDTO;
import com.isapsw.Projekat.dto.PregledDTO;
import com.isapsw.Projekat.repository.*;
import com.isapsw.Projekat.service.PregledService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PregledServiceTest {
    @Mock
    private PregledRepository pregledRepository;

    @Mock
    private SalaRepository salaRepository;

    @Mock
    private LekarRepository lekarRepository;

    @Mock
    private Pregled pregledMock;

    @Mock
    private Klinika klinikaMock;

    @Mock
    private Lekar lekarMock;

    @Mock
    private MedicinskaSestra medSestraMock;

    @Mock
    private Sala salaMock;

    @Mock
    private TipPregleda tipPregledaMock;

    @Mock
    private KlinikaRepository klinikaRepository;

    @Mock
    private TipoviPregledaRepository tipoviPregledaRepository;

    @Mock
    private MedSestraRepository medSestraRepository;

    @Mock
    private PacijentRepository pacijentRepository;

    @InjectMocks
    private PregledService pregledService;


    //Vraca pregled na osnovu id-a
    @Test
    public void getPregledTest() {
        when(pregledRepository.getOne((long)1)).thenReturn(pregledMock);

        Pregled pr = pregledService.getPregledById((long) 1);
        assertEquals(pregledMock, pr);

        verify(pregledRepository, times(1)).getOne((long)1);
        verifyNoMoreInteractions(pregledRepository);
    }

    //Dodavanje novog predefinisanog pregleda
    @Test
    @Transactional
    @Rollback(true)
    public void addPregledTest() throws ParseException {
        Klinika klinika = new Klinika();
        klinika.setNaziv("Klinika 1");
        klinika.setAdresa("Pasiceva 34, Novi Sad");
        klinika.setOpis("Jako fina klinika");
        when(pregledRepository.getOne((long)1)).thenReturn(pregledMock);

        when(klinikaRepository.save(klinika)).thenReturn(klinika);

        when(lekarRepository.getOne((long)1)).thenReturn(lekarMock);
        when(medSestraRepository.getOne((long)1)).thenReturn(medSestraMock);
        when(salaRepository.getOne((long)1)).thenReturn(salaMock);
        when(tipoviPregledaRepository.getOne((long)1)).thenReturn(tipPregledaMock);
        PregledDTO pregledDTO = new PregledDTO();

        TipPregleda tipPregleda = new TipPregleda();
        tipPregleda.setMinimalnoTrajanjeMin(30);

        tipoviPregledaRepository.save(tipPregleda);

        pregledDTO.setLekarId(lekarMock.getId().toString());
        pregledDTO.setMedSestraId(medSestraMock.getId().toString());
        pregledDTO.setSalaId(salaMock.getId().toString());
        pregledDTO.setTipPregledaId("1");
        pregledDTO.setDatum("06-feb-2020 13:30:00");
        pregledDTO.setPopust("0");

        Lekar lekar = new Lekar();
        pregledService.addPregled(pregledDTO);
        Pregled pr = pregledService.getPregledById((long)1);

        assertEquals(pregledMock.getId(), pr.getId());
        when(pregledRepository.save(any(Pregled.class))).thenReturn(new Pregled());
    }

}
