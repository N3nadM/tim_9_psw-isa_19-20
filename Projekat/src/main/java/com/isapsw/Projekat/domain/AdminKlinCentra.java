package com.isapsw.Projekat.domain;

import java.util.ArrayList;
import java.util.List;

public class AdminKlinCentra {

    private String ime;
    private String prezime;
    private String username;
    private String password;
    private List<Klinika> klinike = new ArrayList<>();
    private List<AdminKlinike> administratori = new ArrayList<>();
    private Dijagnoze dijagnoze;
    private Lekovi lekovi;

    public AdminKlinCentra(String ime, String prezime, String username, String password, List<Klinika> klinike, List<AdminKlinike> administratori, Dijagnoze dijagnoze, Lekovi lekovi) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.klinike = klinike;
        this.administratori = administratori;
        this.dijagnoze = dijagnoze;
        this.lekovi = lekovi;
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

    public Dijagnoze getDijagnoze() {
        return dijagnoze;
    }

    public void setDijagnoze(Dijagnoze dijagnoze) {
        this.dijagnoze = dijagnoze;
    }

    public Lekovi getLekovi() {
        return lekovi;
    }

    public void setLekovi(Lekovi lekovi) {
        this.lekovi = lekovi;
    }
}
