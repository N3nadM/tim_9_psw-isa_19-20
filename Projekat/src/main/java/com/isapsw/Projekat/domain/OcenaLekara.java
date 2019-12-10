package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class OcenaLekara {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="korisnik_id", updatable = false, nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Korisnik korisnik;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lekar_id", updatable = false, nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Lekar lekar;

    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private Integer ocena;

    @Column(unique = true)
    private String ocLekaraIdentifier;

    public OcenaLekara() {
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

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }

    public String getOcLekaraIdentifier() {
        return ocLekaraIdentifier;
    }

    public void setOcLekaraIdentifier(String ocLekaraIdentifier) {
        this.ocLekaraIdentifier = ocLekaraIdentifier;
    }

    @Override
    public String toString() {
        return "OcenaLekara{" +
                "id=" + id +
                ", korisnik=" + korisnik +
                ", lekar=" + lekar +
                ", ocena=" + ocena +
                ", ocLekaraIdentifier='" + ocLekaraIdentifier + '\'' +
                '}';
    }
}
