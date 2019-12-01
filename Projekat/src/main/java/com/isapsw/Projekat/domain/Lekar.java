package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Lekar{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Korisnik korisnik;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lekar")
    @JsonIgnore
    private List<Pregled> pregledi = new ArrayList<>();

    @ManyToMany(mappedBy = "lekari")
    @JsonIgnore
    private List<Operacija> operacije = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="klinika_id", updatable = false, nullable = false)
    @JsonIgnore
    private Klinika klinika;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tip_pregleda_id", updatable = false, nullable = false)
    @JsonIgnore
    private TipPregleda tipPregleda;

    public Lekar(){
        super();
        this.pregledi = new ArrayList<>();
        this.operacije = new ArrayList<>();
    }

    public Lekar(Korisnik korisnik, List<Pregled> pregledi, List<Operacija> operacije, Klinika klinika) {
        super();
        this.korisnik = korisnik;
        this.pregledi = pregledi;
        this.operacije = operacije;
        this.klinika = klinika;
    }

    public List<Pregled> getPregledi() {
        return pregledi;
    }

    public void setPregledi(List<Pregled> pregledi) {
        this.pregledi = pregledi;
    }

    public List<Operacija> getOperacije() {
        return operacije;
    }

    public void setOperacije(List<Operacija> operacije) {
        this.operacije = operacije;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }

    public TipPregleda getTipPregleda() {
        return tipPregleda;
    }

    public void setTipPregleda(TipPregleda tipPregleda) {
        this.tipPregleda = tipPregleda;
    }
}
