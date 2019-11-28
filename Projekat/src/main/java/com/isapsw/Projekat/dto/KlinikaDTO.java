package com.isapsw.Projekat.dto;

public class KlinikaDTO {
    private Long id;
    private String naziv;
    private String opis;
    private String adresa;

    public KlinikaDTO(Long id, String naziv, String opis, String adresa) {
        this.id=id;
        this.naziv = naziv;
        this.opis = opis;
        this.adresa = adresa;
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}
