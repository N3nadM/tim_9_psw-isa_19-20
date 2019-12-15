package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MedicinskaSestra{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Korisnik korisnik;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "medicinskaSestra")
    private List<Recept> recepti = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="klinika_id", updatable = false, nullable = false)
    @JsonIgnore
    private Klinika klinika;

    private LocalTime pocetakRadnogVremena;
    private LocalTime krajRadnogVremena;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "medicinskaSestra")
    @JsonIgnore
    private List<Pregled> pregledi = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "medicinskaSestra")
    @JsonIgnore
    private List<Operacija> operacije = new ArrayList<>();

    public MedicinskaSestra(){
        super();
        this.recepti = new ArrayList<>();
    }

    public MedicinskaSestra(Korisnik korisnik, List<Recept> recepti, Klinika klinika) {
        super();
        this.korisnik = korisnik;
        this.recepti = recepti;
        this.klinika = klinika;
    }

    public Long getId() { return id; }
    
    public void setId(Long id) { this.id = id; }

    public List<Recept> getRecepti() { return recepti; }

    public void setRecepti(List<Recept> recepti) { this.recepti = recepti; }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
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

    public LocalTime getPocetakRadnogVremena() {
        return pocetakRadnogVremena;
    }

    public void setPocetakRadnogVremena(LocalTime pocetakRadnogVremena) {
        this.pocetakRadnogVremena = pocetakRadnogVremena;
    }

    public LocalTime getKrajRadnogVremena() {
        return krajRadnogVremena;
    }

    public void setKrajRadnogVremena(LocalTime krajRadnogVremena) {
        this.krajRadnogVremena = krajRadnogVremena;
    }
}
