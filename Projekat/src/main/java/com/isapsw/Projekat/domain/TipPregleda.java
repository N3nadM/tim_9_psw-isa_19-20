package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TipPregleda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private Integer cenaPregleda;

    @NotBlank
    private Integer cenaOperacije;

    @NotBlank
    @Column(nullable = false)
    private String naziv;

    private Integer minimalnoTrajanjeMin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="klinika_id", updatable = false, nullable = false)
    @JsonIgnore
    private Klinika klinika;

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
        naziv = naziv;
    }
}
