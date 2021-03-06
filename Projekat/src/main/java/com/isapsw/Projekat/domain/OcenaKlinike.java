package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class OcenaKlinike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="korisnik_id", updatable = false, nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Korisnik korisnik;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="klinika_id", updatable = false, nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Klinika klinika;


    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private Integer ocena;

    @Column(unique = true)
    private String ocKlinikeIdentifier; // Moze biti formata idKlinike - idKorisnika, po ovome cemo proveravati da li postoji ili ne postoji

    public OcenaKlinike() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOcKlinikeIdentifier() {
        return ocKlinikeIdentifier;
    }

    public void setOcKlinikeIdentifier(String ocKlinikeIdentifier) {
        this.ocKlinikeIdentifier = ocKlinikeIdentifier;
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

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }
}
