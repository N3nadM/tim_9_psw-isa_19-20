package com.isapsw.Projekat.domain;

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
    private List<Pacijent> pacijenti = new ArrayList<>();

    //dodati listu slobodnih termina

    private Double ocena = 0.0;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "klinika", orphanRemoval = true)
    private List<Sala> sale = new ArrayList<>();
    //dodati cenovnik

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "klinika")
    private List<AdminKlinike> adminiKlinike = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "klinika")
    private List<MedicinskaSestra> medicinskeSestre = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "klinika")
    private List<Lekar> lekari = new ArrayList<>();

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
        this.adminiKlinike = adminiKlinike;
        this.medicinskeSestre = new ArrayList<>();
        this.lekari = new ArrayList<>();
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
}
