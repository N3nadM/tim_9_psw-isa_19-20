package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Broj sale + naziv klinike -- kasnije dodati naziv klinike
    @Column(unique = true, nullable = false)
    private String salaIdentifier;

    @Column(nullable = false)
    private String naziv;

    @OneToMany(mappedBy = "sala", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Pregled> pregled = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sala")
    @JsonIgnore
    private List<Operacija> operacija = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="klinika_id", updatable = false, nullable = false)
    @JsonIgnore
    private Klinika klinika;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = false)
    private Date datumKreiranja;

    private boolean aktivna;

    public Sala() {
    }

    public List<Operacija> getOperacija() {
        return operacija;
    }

    public void setOperacija(List<Operacija> operacija) {
        this.operacija = operacija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalaIdentifier() {
        return salaIdentifier;
    }

    public void setSalaIdentifier(String salaIdentifier) {
        this.salaIdentifier = salaIdentifier;
    }

    public List<Pregled> getPregled() {
        return pregled;
    }

    public void setPregled(List<Pregled> pregled) {
        this.pregled = pregled;
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "id=" + id +
                ", salaIdentifier='" + salaIdentifier + '\'' +
                ", pregled=" + pregled +
                ", klinika=" + klinika +
                ", datumKreiranja=" + datumKreiranja +
                '}';
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @PrePersist
    protected void onCreate(){
        this.datumKreiranja = new Date();
    }

    public boolean isAktivna() {
        return aktivna;
    }

    public void setAktivna(boolean aktivna) {
        this.aktivna = aktivna;
    }
}
