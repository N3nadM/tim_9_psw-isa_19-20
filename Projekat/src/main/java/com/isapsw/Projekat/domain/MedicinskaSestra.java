package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
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
}
