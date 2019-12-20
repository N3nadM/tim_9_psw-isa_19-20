package com.isapsw.Projekat.dto;

import java.util.List;

public class DijagnozaDTO {

    private Long id;
    private String naziv;
    private String sifra;
    private List<String> lekovi;

    public DijagnozaDTO() {
    }

    public DijagnozaDTO(Long id, String naziv, String sifra, List<String> lekovi) {
        this.id = id;
        this.naziv = naziv;
        this.sifra = sifra;
        this.lekovi = lekovi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public List<String> getLekovi() {
        return lekovi;
    }

    public void setLekovi(List<String> lekovi) {
        this.lekovi = lekovi;
    }
}
