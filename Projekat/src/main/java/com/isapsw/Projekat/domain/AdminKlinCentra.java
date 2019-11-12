package com.isapsw.Projekat.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AdminKlinCentra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ime;
    
    private String prezime;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "adminKlinCentra", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Dijagnoza dijagnoze;

    @OneToMany(mappedBy = "adminKlinCentra", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Lek lekovi;

    @OneToMany(mappedBy = "adminKlinCentra", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Klinika> klinike = new ArrayList<>();

    @OneToMany(mappedBy = "adminKlinCentra", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AdminKlinike> administratori = new ArrayList<>();

    @OneToMany(mappedBy = "adminKlinCentra", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Pacijent> zahteviPacijenata = new ArrayList<>();



    public AdminKlinCentra(){
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
        this.dijagnoze = dijagnoze;
        this.lekovi = lekovi;
        this.klinike = new ArrayList<>();
        this.administratori = new ArrayList<>();
        this.zahteviPacijenata = new ArrayList<>();

    }

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

    public Dijagnoza getDijagnoze() { return dijagnoze; }

    public void setDijagnoze(Dijagnoza dijagnoze) { this.dijagnoze = dijagnoze; }

    public Lek getLekovi() { return lekovi; }

    public void setLekovi(Lek lekovi) { this.lekovi = lekovi; }

    public List<Pacijent> getZahteviPacijenata() { return zahteviPacijenata; }

    public void setZahteviPacijenata(List<Pacijent> zahteviPacijenata) { this.zahteviPacijenata = zahteviPacijenata; }
}
