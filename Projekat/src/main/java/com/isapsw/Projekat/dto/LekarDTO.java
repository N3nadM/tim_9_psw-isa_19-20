package com.isapsw.Projekat.dto;

public class LekarDTO {

    private Long id;
    private String ime;
    private String prezime;
    private String grad;
    private String drzava;
    private String adresa;
    private String telefon;
    private String email;
    private String password;
    private Long klinikaId;
    private Long tipPregledaId;

    public  LekarDTO() {super();}

    public LekarDTO(String ime, String prezime, String grad, String drzava, String adresa, String telefon, String email, String password, Long klinikaId, Long tipPregledaId) {
        this.ime = ime;
        this.prezime = prezime;
        this.grad = grad;
        this.drzava = drzava;
        this.adresa = adresa;
        this.telefon = telefon;
        this.email = email;
        this.password = password;
        this.klinikaId = klinikaId;
        this.tipPregledaId = tipPregledaId;
    }

    public Long getTipPregledaId() {
        return tipPregledaId;
    }

    public void setTipPregledaId(Long tipPrgegledaId) {
        this.tipPregledaId = tipPrgegledaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
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

    public Long getKlinikaId() {
        return klinikaId;
    }

    public void setKlinikaId(Long klinikaId) {
        this.klinikaId = klinikaId;
    }
}
