package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TipPregleda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer cenaPregleda;

    @NotNull
    private Integer cenaOperacije;


    @NotBlank( message = "Neophodno je uneti naziv tipa pregleda.")
    @Column(nullable = false)
    private String naziv;

    private Integer minimalnoTrajanjeMin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="klinika_id", updatable = false, nullable = false)
    @JsonIgnore
    private Klinika klinika;

    private boolean aktivan;

    public TipPregleda() {
    }

    public TipPregleda(String naziv) {
        naziv = naziv;
    }

    public Integer getMinimalnoTrajanjeMin() {
        return minimalnoTrajanjeMin;
    }

    public void setMinimalnoTrajanjeMin(Integer minimalnoTrajanjeMin) {
        this.minimalnoTrajanjeMin = minimalnoTrajanjeMin;
    }

    public Integer getCenaPregleda() {
        return cenaPregleda;
    }

    public void setCenaPregleda(Integer cenaPregleda) {
        this.cenaPregleda = cenaPregleda;
    }

    public Integer getCenaOperacije() {
        return cenaOperacije;
    }

    public void setCenaOperacije(Integer cenaOperacije) {
        this.cenaOperacije = cenaOperacije;
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
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

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }
}
