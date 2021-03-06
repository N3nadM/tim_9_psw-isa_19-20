package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Klinika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Neophodno je uneti naziv klinike.")
    private String naziv;

    @NotBlank(message = "Neophodno je uneti adresu klinike.")
    private String adresa;

    private String opis;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "pacijenti_klinike",
            joinColumns = @JoinColumn(name = "klinika_id"),
            inverseJoinColumns = @JoinColumn(name = "pacijent_id"))
    @JsonIgnore
    private List<Pacijent> pacijenti = new ArrayList<>();

    //dodati listu slobodnih termina

    private Double ocena = 0.0;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "klinika", orphanRemoval = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Sala> sale = new ArrayList<>();
    //dodati cenovnik

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "klinika")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<TipPregleda> tipPregleda = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "klinika")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<AdminKlinike> adminiKlinike = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "klinika")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<MedicinskaSestra> medicinskeSestre = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "klinika")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Lekar> lekari = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "klinika")
    @JsonIgnore
    private List<ZahtevOdsustvo> zahteviOdsustvo= new ArrayList<>();

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "klinika")
    @JsonIgnore
    private List<ZahtevOdmor> zahteviOdmor= new ArrayList<>();

    public Klinika() {
        this.adminiKlinike = new ArrayList<>();
        this.sale = new ArrayList<>();
        this.medicinskeSestre = new ArrayList<>();
        this.lekari = new ArrayList<>();
    }

    public Klinika(String naziv, String adresa, String opis, List<Sala> sale, List<AdminKlinike> adminiKlinike) {
        this.naziv = naziv;
        this.adresa = adresa;
        this.opis = opis;
        this.sale = sale;
    }

    public Double getOcena() {
        return ocena;
    }

    public void setOcena(Double ocena) {
        this.ocena = ocena;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public List<Sala> getSale() {
        return sale;
    }

    public void setSale(List<Sala> sale) {
        this.sale = sale;
    }

    public List<AdminKlinike> getAdminiKlinike() {
        return adminiKlinike;
    }

    public void setAdminiKlinike(List<AdminKlinike> adminiKlinike) {
        this.adminiKlinike = adminiKlinike;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MedicinskaSestra> getMedicinskeSestre() {
        return medicinskeSestre;
    }

    public void setMedicinskeSestre(List<MedicinskaSestra> medicinskeSestre) {
        this.medicinskeSestre = medicinskeSestre;
    }

    public List<Lekar> getLekari() {
        return lekari;
    }

    public void setLekari(List<Lekar> lekari) {
        this.lekari = lekari;
    }

    public List<Pacijent> getPacijenti() {
        return pacijenti;
    }

    public void setPacijenti(List<Pacijent> pacijenti) {
        this.pacijenti = pacijenti;
    }

    public List<TipPregleda> getTipPregleda() {
        return tipPregleda;
    }

    public void setTipPregleda(List<TipPregleda> tipPregleda) {
        this.tipPregleda = tipPregleda;
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
