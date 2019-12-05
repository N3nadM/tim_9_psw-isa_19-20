package com.isapsw.Projekat.dto;

public class ZdrKartonDTO {

    private Long id;
    private Integer tezina;
    private Integer visina;
    private float dioptrija;
    private String krvnaGrupa;

    public ZdrKartonDTO() {
    }

    public ZdrKartonDTO(Long id, Integer tezina, Integer visina, float dioptrija, String krvnaGrupa) {
        this.id = id;
        this.tezina = tezina;
        this.visina = visina;
        this.dioptrija = dioptrija;
        this.krvnaGrupa = krvnaGrupa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTezina() {
        return tezina;
    }

    public void setTezina(Integer tezina) {
        this.tezina = tezina;
    }

    public Integer getVisina() {
        return visina;
    }

    public void setVisina(Integer visina) {
        this.visina = visina;
    }

    public float getDioptrija() {
        return dioptrija;
    }

    public void setDioptrija(float dioptrija) {
        this.dioptrija = dioptrija;
    }

    public String getKrvnaGrupa() {
        return krvnaGrupa;
    }

    public void setKrvnaGrupa(String krvnaKrupa) {
        this.krvnaGrupa = krvnaKrupa;
    }
}
