package com.isapsw.Projekat.Rezervacije.controller;

import com.isapsw.Projekat.Rezervacije.TestUtils;
import com.isapsw.Projekat.Rezervacije.constants.PregledConst;
import com.isapsw.Projekat.controller.UserController;
import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.PacijentDTO;
import com.isapsw.Projekat.dto.PregledDTO;
import com.isapsw.Projekat.payload.LoginRequest;
import com.isapsw.Projekat.security.JWTTokenProvider;
import com.isapsw.Projekat.service.EmailService;
import com.isapsw.Projekat.service.PregledService;
import com.isapsw.Projekat.service.ValidationErrorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.isapsw.Projekat.security.Konstante.TOKEN_BEARER_PREFIX;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
public class PregledControllerTest {

    private static final String URL_PREFIX = "/api/pregled";

    private MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());


    private MockMvc mockMvc;

    @MockBean
    private PregledService pregledServiceMock;

    @MockBean
    private LoginRequest loginRequestMock;

    private UserController userController;

    private String jwt;

    @MockBean
    private JavaMailSender javaMailSendermock;

    @Autowired
    private ValidationErrorService validationErrorService;

    @Autowired
    private JWTTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @MockBean
    private EmailService emailServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TestRestTemplate restTemplate;

    @PostConstruct
    public void setup() {
        this.mockMvc = MockMvcBuilders.
                webAppContextSetup(webApplicationContext).build();
    }

    public void login(String username, String password){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        ResponseEntity<String> jwtEntity = restTemplate.postForEntity("/api/users/login", loginRequest, String.class);
        System.out.println(jwtEntity);
        jwt = TOKEN_BEARER_PREFIX +  jwtEntity;
    }

    @Test
    public void testGetPregledById() throws Exception {
        login("admin@gmail.com", "admin"); //logovanje administratora klinike
        Pregled pregled = new Pregled();

        pregled.setId(PregledConst.PREGLED_ID);
        pregled.setIzvestaj(PregledConst.PREGLED_IZVESTAJ);
        pregled.setDatumPocetka(PregledConst.PREGLED_DATUMPOCETKA);

        Sala sala = new Sala();
        sala.setId(1L);
        pregled.setSala(sala);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        when(pregledServiceMock.getPregledById((long)1)).thenReturn(pregled);
        MvcResult result = mockMvc.perform(get(URL_PREFIX + "/zahtev/1").header("Authorization", jwt))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(PregledConst.PREGLED_ID))
                .andExpect(jsonPath("$.izvestaj").value(PregledConst.PREGLED_IZVESTAJ))
                .andExpect(jsonPath("$.datumPocetka").value(format.format(PregledConst.PREGLED_DATUMPOCETKA)))
                .andExpect(jsonPath("$.sala.id").value(sala.getId())).andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println(content);
    }


    @Test
    public void testAddPredefinisaniPregled() throws Exception {
        login("admin@gmail.com", "admin"); //logovanje administratora klinike
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        PregledDTO pregledDTO = new PregledDTO((long)1, "10","1","1","1","1", format.format(PregledConst.PREGLED_DATUMPOCETKA));

        Pregled pregled = new Pregled();

        pregled.setId((long)1);
        pregled.setDatumPocetka(PregledConst.PREGLED_DATUMPOCETKA);
        pregled.setPopust(10);
        Lekar lekar = new Lekar();
        lekar.setId((long)1);
        pregled.setLekar(lekar);
        Sala sala = new Sala();
        sala.setId(1L);
        pregled.setSala(sala);
        MedicinskaSestra medicinskaSestra = new MedicinskaSestra();
        medicinskaSestra.setId((long)1);
        pregled.setMedicinskaSestra(medicinskaSestra);
        TipPregleda tipPregleda = new TipPregleda();
        tipPregleda.setId((long)1);
        pregled.setTipPregleda(tipPregleda);

        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        Mockito.when(pregledServiceMock.addPregled(any(PregledDTO.class))).thenReturn(pregled);
        MvcResult result = mockMvc.perform(post(URL_PREFIX).header("Authorization", jwt)
                .contentType(contentType)
                .content(TestUtils.json(pregledDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value((long)1))
                .andExpect(jsonPath("$.datumPocetka").value(format2.format(PregledConst.PREGLED_DATUMPOCETKA)))
                .andExpect(jsonPath("$.sala.id").value((long)1))
                .andExpect(jsonPath("$.tipPregleda.id").value((long)1))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println(content);
    }


    @Test
    @Transactional
    public void testZakaziPredefinisaniPregled() throws Exception {
        login("neskexx@gmail.com", "admin"); //logovanje pacijnta

        Korisnik korisnik = new Korisnik();
        korisnik.setId((long)4);

        Pacijent pacijent = new Pacijent();
        pacijent.setId((long)1);
        pacijent.setKorisnik(korisnik);

        Pregled pregled = new Pregled();

        pregled.setId(PregledConst.PREGLED_ID);
        pregled.setDatumPocetka(PregledConst.PREGLED_DATUMPOCETKA);

        Sala sala = new Sala();
        sala.setId(1L);
        pregled.setSala(sala);
        String version = "0";

        Map<String, String> body = new HashMap<>();
        body.put("pregledId", pregled.getId().toString());
        body.put("version", version);

        Mockito.when(pregledServiceMock.getPregledById((long)1)).thenReturn(pregled);
        Mockito.when(pregledServiceMock.zakaziPredefinisaniPregled(korisnik.getId(), pregled.getId().toString(), "0")).thenReturn(true);
        MvcResult result = mockMvc.perform(post(URL_PREFIX + "/zakaziPredefinisani").header("Authorization", jwt)
                .contentType(contentType)
                .content(TestUtils.json(body)))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void testGetPreglediKojiNemajuSalu() throws Exception {
        login("admin@gmail.com", "admin"); //logovanje administratora klinike

        Klinika klinika = new Klinika();
        klinika.setId((long)1);

        Lekar lekar = new Lekar();
        lekar.setId((long)1);

        List<Pregled> pregledi = new ArrayList<>();

        Pregled pregled1 = new Pregled();
        pregled1.setId((long)1);
        pregled1.setIzvestaj(PregledConst.PREGLED_IZVESTAJ + "1");
        pregled1.setDatumPocetka(PregledConst.PREGLED_DATUMPOCETKA);

        Pregled pregled2 = new Pregled();
        pregled2.setId((long)2);
        pregled2.setIzvestaj(PregledConst.PREGLED_IZVESTAJ + "2");
        pregled2.setDatumPocetka(PregledConst.PREGLED_DATUMPOCETKA);

        pregledi.add(pregled1);
        pregledi.add(pregled2);
        lekar.setPregledi(pregledi);
        klinika.setLekari(new ArrayList<>());
        klinika.getLekari().add(lekar);


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        when(pregledServiceMock.getPreglediKojiNemajuSalu("1")).thenReturn(pregledi);

        MvcResult result = mockMvc.perform(get(URL_PREFIX + "/nemajuSalu/1").header("Authorization", jwt))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").value(hasItem(1)))
                .andExpect(jsonPath("$.[*].izvestaj").value(hasItem(PregledConst.PREGLED_IZVESTAJ + "1")))
                .andExpect(jsonPath("$.[*].datumPocetka").value(hasItem(format.format(PregledConst.PREGLED_DATUMPOCETKA))))
                .andReturn();


        String content = result.getResponse().getContentAsString();
        System.out.println(content);


    }


}
