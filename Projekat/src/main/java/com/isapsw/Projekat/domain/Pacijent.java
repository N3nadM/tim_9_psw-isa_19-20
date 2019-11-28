package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pacijent{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Korisnik korisnik;

    @NotBlank(message = "Neophodno je uneti jedinstveni broj zdravstvenog osiguranika.")
    @Column(updatable = false, unique = true)
    private String jbzo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = false)
    private Date datum_kreiranja;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pacijenti")
    private List<Klinika> klinike = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pacijent")
    @JsonIgnore
    private ZdrKarton zdrKarton;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pacijent")
    @JsonIgnore
    private List<Pregled> pregledi = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pacijent")
    @JsonIgnore
    private List<Operacija> operacije = new ArrayList<>();

    public Pacijent() {
    }

    public Pacijent(Zahtev zahtev) {
        this.jbzo = zahtev.getJbzo();
    }

    public ZdrKarton getZdrKarton() {
        return zdrKarton;
    }

    public void setZdrKarton(ZdrKarton zdrKarton) {
        this.zdrKarton = zdrKarton;
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

    public String getJbzo() {
        return jbzo;
    }

    public void setJbzo(String jbzo) {
        this.jbzo = jbzo;
    }

    public Date getDatum_kreiranja() {
        return datum_kreiranja;
    }

    public void setDatum_kreiranja(Date datum_kreiranja) {
        this.datum_kreiranja = datum_kreiranja;
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

    @PrePersist
    protected void onCreate(){
        this.datum_kreiranja = new Date();
    }


}
