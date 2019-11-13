package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ZdrKarton {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pacijent_id",nullable = false)
    @JsonIgnore
    private Pacijent pacijent;

    private Float dioptrija;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zdrKarton")
    private List<Lek> alergijaNaLek = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zdrKarton")
    private List<Dijagnoza> istorijaBolesti = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zdrKarton")
    private List<Recept> izdatiRecepti = new ArrayList<>();

    private Integer visina;

    private Integer tezina;

    private String krvnaGrupa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Float getDioptrija() {
        return dioptrija;
    }

    public void setDioptrija(Float dioptrija) {
        this.dioptrija = dioptrija;
    }

    public List<Lek> getAlergijaNaLek() {
        return alergijaNaLek;
    }

    public void setAlergijaNaLek(List<Lek> alergijaNaLek) {
        this.alergijaNaLek = alergijaNaLek;
    }

    public List<Dijagnoza> getIstorijaBolesti() {
        return istorijaBolesti;
    }

    public void setIstorijaBolesti(List<Dijagnoza> istorijaBolesti) {
        this.istorijaBolesti = istorijaBolesti;
    }

    public List<Recept> getIzdatiRecepti() {
        return izdatiRecepti;
    }

    public void setIzdatiRecepti(List<Recept> izdatiRecepti) {
        this.izdatiRecepti = izdatiRecepti;
    }

    public Integer getVisina() {
        return visina;
    }

    public void setVisina(Integer visina) {
        this.visina = visina;
    }

    public Integer getTezina() {
        return tezina;
    }

    public void setTezina(Integer tezina) {
        this.tezina = tezina;
    }

    public String getKrvnaGrupa() {
        return krvnaGrupa;
    }

    public void setKrvnaGrupa(String krvnaGrupa) {
        this.krvnaGrupa = krvnaGrupa;
    }
}
