package com.isapsw.Projekat.domain;

import java.util.UUID;

public class Pacijent {

    private UUID id;
    private String ime;
    private String prezime;
    private String username;
    private String email;
    private String password;
    private ZdrKarton zdrKarton;
    private Pregled_Operacija pregled_operacija = null;

    public Pacijent(UUID id, String ime, String prezime, String username, String email, String password, ZdrKarton zdrKarton) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.email = email;
        this.password = password;
        this.zdrKarton = zdrKarton;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ZdrKarton getZdrKarton() {
        return zdrKarton;
    }

    public void setZdrKarton(ZdrKarton zdrKarton) {
        this.zdrKarton = zdrKarton;
    }

    public Pregled_Operacija getPregled_operacija() {
        return pregled_operacija;
    }

    public void setPregled_operacija(Pregled_Operacija pregled_operacija) {
        this.pregled_operacija = pregled_operacija;
    }
}
