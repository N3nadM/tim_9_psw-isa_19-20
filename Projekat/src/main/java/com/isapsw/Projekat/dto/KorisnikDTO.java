package com.isapsw.Projekat.dto;

import com.isapsw.Projekat.domain.AdminKlinike;
import com.isapsw.Projekat.domain.Lekar;

public class KorisnikDTO {
    private Long id;
    private String ime;
    private String prezime;
    private String grad;
    private String drzava;
    private String adresa;
    private String telefon;
    private String email;
    private String password;


    public KorisnikDTO() {
    }

    public KorisnikDTO(Long id, String ime, String prezime, String grad, String drzava, String adresa, String telefon) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.grad = grad;
        this.drzava = drzava;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    public KorisnikDTO(Long id, String ime, String prezime, String grad, String drzava, String adresa, String telefon, String email, String password) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.grad = grad;
        this.drzava = drzava;
        this.adresa = adresa;
        this.telefon = telefon;
        this.email = email;
        this.password = password;
    }

    public KorisnikDTO(AdminKlinikeDTO adminKlinikeDTO){
        this.id = adminKlinikeDTO.getId();
        this.ime = adminKlinikeDTO.getIme();
        this.prezime = adminKlinikeDTO.getPrezime();
        this.grad = adminKlinikeDTO.getGrad();
        this.drzava = adminKlinikeDTO.getDrzava();
        this.adresa = adminKlinikeDTO.getAdresa();
        this.telefon = adminKlinikeDTO.getTelefon();
        this.email = adminKlinikeDTO.getEmail();
        this.password = adminKlinikeDTO.getPassword();
    }

    public KorisnikDTO(AdminKCDTO adminKCDTO){
        this.id = adminKCDTO.getId();
        this.ime = adminKCDTO.getIme();
        this.prezime = adminKCDTO.getPrezime();
        this.grad = adminKCDTO.getGrad();
        this.drzava = adminKCDTO.getDrzava();
        this.adresa = adminKCDTO.getAdresa();
        this.telefon = adminKCDTO.getTelefon();
        this.email = adminKCDTO.getEmail();
        this.password = adminKCDTO.getPassword();
    }

    public KorisnikDTO(LekarDTO l){
        this.id = l.getId();
        this.ime = l.getIme();
        this.prezime = l.getPrezime();
        this.grad = l.getGrad();
        this.drzava = l.getDrzava();
        this.adresa = l.getAdresa();
        this.telefon = l.getTelefon();
        this.email = l.getEmail();
        this.password = l.getPassword();
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
}
