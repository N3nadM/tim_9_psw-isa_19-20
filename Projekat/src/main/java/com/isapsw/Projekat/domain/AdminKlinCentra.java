package com.isapsw.Projekat.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AdminKlinCentra{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Korisnik korisnik;


    public AdminKlinCentra(){
        super();
    }

    public AdminKlinCentra( Korisnik korisnik, Dijagnoza dijagnoze, Lek lekovi,  List<AdminKlinike> administratori, List<Pacijent> zahteviPacijenata) {
        super();
        this.korisnik = korisnik;
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
}
