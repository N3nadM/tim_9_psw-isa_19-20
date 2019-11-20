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

    @OneToMany(mappedBy = "adminKlinCentra", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dijagnoza> dijagnoze = new ArrayList<>();

    @OneToMany(mappedBy = "adminKlinCentra", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lek> lekovi = new ArrayList<>();

    @OneToMany(mappedBy = "adminKlinCentra", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AdminKlinike> administratori = new ArrayList<>();

    public AdminKlinCentra(){
        super();
        this.lekovi =  new ArrayList<>();
        this.dijagnoze =  new ArrayList<>();
        this.administratori = new ArrayList<>();
    }

    public AdminKlinCentra( Korisnik korisnik, Dijagnoza dijagnoze, Lek lekovi,  List<AdminKlinike> administratori, List<Pacijent> zahteviPacijenata) {
        super();
        this.korisnik = korisnik;
        this.dijagnoze =  new ArrayList<>();
        this.lekovi =  new ArrayList<>();
        this.administratori = new ArrayList<>();
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


    public List<AdminKlinike> getAdministratori() {
        return administratori;
    }

    public void setAdministratori(List<AdminKlinike> administratori) {
        this.administratori = administratori;
    }

    public List<Dijagnoza> getDijagnoze() {
        return dijagnoze;
    }

    public void setDijagnoze(List<Dijagnoza> dijagnoze) {
        this.dijagnoze = dijagnoze;
    }

    public List<Lek> getLekovi() {
        return lekovi;
    }

    public void setLekovi(List<Lek> lekovi) {
        this.lekovi = lekovi;
    }
}
