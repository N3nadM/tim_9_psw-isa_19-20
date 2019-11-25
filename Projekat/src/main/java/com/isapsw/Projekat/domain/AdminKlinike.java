package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class AdminKlinike{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Korisnik korisnik;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="klinika_id", updatable = false, nullable = false)
    @JsonIgnore
    private Klinika klinika;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="adminKlinCentra_id", updatable = false)
    @JsonIgnore
    private AdminKlinCentra adminKlinCentra;

    public AdminKlinike() {
        super();
    }



    public AdminKlinike(Korisnik korisnik, Klinika klinika, AdminKlinCentra adminKlinCentra) {
        super();
        this.korisnik = korisnik;
        this.klinika = klinika;
        this.adminKlinCentra = adminKlinCentra;
    }

    public AdminKlinCentra getAdminKlinCentra() {
        return adminKlinCentra;
    }

    public void setAdminKlinCentra(AdminKlinCentra adminKlinCentra) {
        this.adminKlinCentra = adminKlinCentra;
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

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    public String toString() {
        return "AdminKlinike{" +
                "id=" + id +
                ", klinika=" + klinika +
                '}';
    }
}
