package com.isapsw.Projekat.dto;

public class TipPregledaDTO {

    private Long id;

    private String naziv;
    private String cenaPregleda;
    private String cenaOperacije;
    private String minimalnoTrajanjeMin;
    private Long klinikaId;

    public TipPregledaDTO( String naziv,String cenaPregleda, String cenaOperacije, String minimalnoTrajanjeMin, Long klinikaId) {
        this.cenaPregleda = cenaPregleda;
        this.cenaOperacije = cenaOperacije;
        this.naziv = naziv;
        this.minimalnoTrajanjeMin = minimalnoTrajanjeMin;
        this.klinikaId = klinikaId;
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

    public Long getKlinikaId() {
        return klinikaId;
    }

    public void setKlinikaId(Long klinikaId) {
        this.klinikaId = klinikaId;
    }

    public String getCenaPregleda() {
        return cenaPregleda;
    }

    public void setCenaPregleda(String cenaPregleda) {
        this.cenaPregleda = cenaPregleda;
    }

    public String getCenaOperacije() {
        return cenaOperacije;
    }

    public void setCenaOperacije(String cenaOperacije) {
        this.cenaOperacije = cenaOperacije;
    }

    public String getMinimalnoTrajanjeMin() {
        return minimalnoTrajanjeMin;
    }

    public void setMinimalnoTrajanjeMin(String minimalnoTrajanjeMin) {
        this.minimalnoTrajanjeMin = minimalnoTrajanjeMin;
    }
}
