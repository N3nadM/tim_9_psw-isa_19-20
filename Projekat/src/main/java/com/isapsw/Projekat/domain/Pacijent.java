package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    private Date datum_kreiranja;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pacijent")
    private ZdrKarton zdrKarton;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pacijent")
    private List<Pregled> pregledi = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pacijent")
    private List<Operacija> operacije = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="adminKlinCentra_id", updatable = false, nullable = true)
    @JsonIgnore
    private AdminKlinCentra adminKlinCentra;

    public Pacijent() {
        super();
    }

    public Pacijent(Korisnik korisnik, @NotBlank(message = "Neophodno je uneti jedinstveni broj zdravstvenog osiguranika.") String jbzo, Date datum_kreiranja, ZdrKarton zdrKarton, AdminKlinCentra adminKlinCentra) {
        super();
        this.korisnik = korisnik;
        this.jbzo = jbzo;
        this.datum_kreiranja = datum_kreiranja;
        this.zdrKarton = zdrKarton;
        this.pregledi = new ArrayList<>();
        this.operacije = new ArrayList<>();
        this.adminKlinCentra = adminKlinCentra;
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

    public AdminKlinCentra getAdminKlinCentra() {
        return adminKlinCentra;
    }

    public void setAdminKlinCentra(AdminKlinCentra adminKlinCentra) {
        this.adminKlinCentra = adminKlinCentra;
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
