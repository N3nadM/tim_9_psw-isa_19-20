package com.isapsw.Projekat.Rezervacije.controller;

import com.isapsw.Projekat.Rezervacije.constants.PregledConst;
import com.isapsw.Projekat.controller.UserController;
import com.isapsw.Projekat.domain.Pregled;
import com.isapsw.Projekat.domain.Sala;
import com.isapsw.Projekat.dto.PregledDTO;
import com.isapsw.Projekat.payload.LoginRequest;
import com.isapsw.Projekat.security.JWTTokenProvider;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;

import static com.isapsw.Projekat.security.Konstante.TOKEN_BEARER_PREFIX;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
public class PregledControllerTest {

    private static final String URL_PREFIX = "/api/pregled";

    private MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));


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
        ResponseEntity<?> jwtEntity = restTemplate.postForEntity("/api/users/login", loginRequest, String.class);
        System.out.println(jwtEntity);
        jwt = TOKEN_BEARER_PREFIX +  jwtEntity;
    }

    @Test
    public void testGetPregledById() throws Exception {
        login("admin@gmail.com", "admin");
        Pregled pregled = new Pregled();
        Sala sala = new Sala();
        sala.setId(1L);
        pregled.setId(PregledConst.PREGLED_ID);
        pregled.setIzvestaj(PregledConst.PREGLED_IZVESTAJ);
        pregled.setDatumPocetka(PregledConst.PREGLED_DATUMPOCETKA);
        pregled.setSala(sala);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Mockito.when(pregledServiceMock.getPregledById((long)1)).thenReturn(pregled);
        MvcResult result = mockMvc.perform(get(URL_PREFIX + "/zahtev/1").header("Authorization", jwt))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(PregledConst.PREGLED_ID))
                .andExpect(jsonPath("$.izvestaj").value(PregledConst.PREGLED_IZVESTAJ))
                .andExpect(jsonPath("$.datumPocetka").value(format.format(PregledConst.PREGLED_DATUMPOCETKA))).andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println(content);
    }

}
