package com.isapsw.Projekat.domain;

public class AdminKlinike {
    private String ime;
    private String prezime;
    private String username;
    private String password;
    private String email;

    public AdminKlinike(String ime, String prezime, String username, String password, String email) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setIme(String ime) {
        this.ime = ime;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
