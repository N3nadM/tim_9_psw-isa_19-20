package com.isapsw.Projekat.domain;

public class AdminKlinCentra {

    private String ime;
    private String prezime;
    private String username;
    private String password;
    //private List<Klinika> klinike = new ArrayList<>();
    //private List<AdminKlinike> administratori = new ArrayList<>();
    //private Dijagnoze dijagnoze;
    //private Lekovi lekovi;


    public AdminKlinCentra(String ime, String prezime, String username, String password) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
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
}
