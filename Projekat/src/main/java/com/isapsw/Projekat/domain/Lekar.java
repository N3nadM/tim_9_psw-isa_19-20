package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalTime;
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

    private LocalTime pocetakRadnogVremena;
    private LocalTime krajRadnogVremena;

    private Double ocena = 0.0;

    @OneToMany( fetch = FetchType.LAZY, mappedBy = "lekar")
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TipPregleda tipPregleda;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lekar")
    @JsonIgnore
    private List<Odsustvo> odsustvo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lekar")
    @JsonIgnore
    private List<Odmor> odmor;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lekar")
    @JsonIgnore
    private List<ZahtevOdsustvo> zahteviOdsustvo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lekar")
    @JsonIgnore
    private List<ZahtevOdmor> zahteviOdmor;

    private boolean aktivan;

    public Lekar(){
        super();
    }

    public Lekar(Korisnik korisnik, List<Pregled> pregledi, List<Operacija> operacije, Klinika klinika) {
        super();
        this.korisnik = korisnik;
        this.pregledi = pregledi;
        this.operacije = operacije;
        this.klinika = klinika;
    }

    public Double getOcena() {
        return ocena;
    }

    public void setOcena(Double ocena) {
        this.ocena = ocena;
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

    public TipPregleda getTipPregleda() {
        return tipPregleda;
    }

    public void setTipPregleda(TipPregleda tipPregleda) {
        this.tipPregleda = tipPregleda;
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

    public List<Odsustvo> getOdsustvo() {
        return odsustvo;
    }

    public void setOdsustvo(List<Odsustvo> odsustvo) {
        this.odsustvo = odsustvo;
    }

    public List<Odmor> getOdmor() {
        return odmor;
    }

    public void setOdmor(List<Odmor> odmor) {
        this.odmor = odmor;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public List<ZahtevOdsustvo> getZahteviOdsustvo() {
        return zahteviOdsustvo;
    }

    public void setZahteviOdsustvo(List<ZahtevOdsustvo> zahteviOdsustvo) {
        this.zahteviOdsustvo = zahteviOdsustvo;
    }

    public List<ZahtevOdmor> getZahteviOdmor() {
        return zahteviOdmor;
    }

    public void setZahteviOdmor(List<ZahtevOdmor> zahteviOdmor) {
        this.zahteviOdmor = zahteviOdmor;
    }
}
