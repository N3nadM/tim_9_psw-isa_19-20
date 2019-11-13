package com.isapsw.Projekat.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AdminKlinCentra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Neophodno je uneti ime.")
    private String ime;

    @NotBlank(message = "Neophodno je uneti prezime.")
    private String prezime;

    @NotBlank(message = "Neophodno je uneti korisnicko ime.")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Neophodno je uneti email.")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Neophodno je uneti password.")
    @Size(min = 5)
    private String password;

    @OneToMany(mappedBy = "adminKlinCentra", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Dijagnoza> dijagnoze = new ArrayList<>();

    @OneToMany(mappedBy = "adminKlinCentra", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Lek> lekovi = new ArrayList<>();

    @OneToMany(mappedBy = "adminKlinCentra", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Klinika> klinike = new ArrayList<>();

    @OneToMany(mappedBy = "adminKlinCentra", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AdminKlinike> administratori = new ArrayList<>();

    @OneToMany(mappedBy = "adminKlinCentra", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Pacijent> zahteviPacijenata = new ArrayList<>();

    public AdminKlinCentra(){
        this.lekovi =  new ArrayList<>();
        this.dijagnoze =  new ArrayList<>();
        this.klinike = new ArrayList<>();
        this.administratori = new ArrayList<>();
        this.zahteviPacijenata = new ArrayList<>();
    }

    public AdminKlinCentra(String ime, String prezime, String username, String email, String password, Dijagnoza dijagnoze, Lek lekovi, List<Klinika> klinike, List<AdminKlinike> administratori, List<Pacijent> zahteviPacijenata) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dijagnoze =  new ArrayList<>();
        this.lekovi =  new ArrayList<>();
        this.klinike = new ArrayList<>();
        this.administratori = new ArrayList<>();
        this.zahteviPacijenata = new ArrayList<>();

    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Klinika> getKlinike() {
        return klinike;
    }

    public void setKlinike(List<Klinika> klinike) {
        this.klinike = klinike;
    }

    public List<AdminKlinike> getAdministratori() {
        return administratori;
    }

    public void setAdministratori(List<AdminKlinike> administratori) {
        this.administratori = administratori;
    }

    public List<Dijagnoza> getDijagnoze() {
        return dijagnoze;
    }

    public void setDijagnoze(List<Dijagnoza> dijagnoze) {
        this.dijagnoze = dijagnoze;
    }

    public List<Lek> getLekovi() {
        return lekovi;
    }

    public void setLekovi(List<Lek> lekovi) {
        this.lekovi = lekovi;
    }

    public List<Pacijent> getZahteviPacijenata() { return zahteviPacijenata; }

    public void setZahteviPacijenata(List<Pacijent> zahteviPacijenata) { this.zahteviPacijenata = zahteviPacijenata; }
}
