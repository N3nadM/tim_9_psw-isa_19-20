package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class Pregled {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String tipPregleda;

    @NotBlank
    private Integer cena;

    private Integer popust;

    private String izvestaj;

    @JsonFormat(pattern = "dd-M-yyyy hh:mm")
    private Date datumPregleda;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    private Date datumKreiranja;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pacijent_id", updatable = false, nullable = false)
    @JsonIgnore
    private Pacijent pacijent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="lekar_id", updatable = false, nullable = false)
    @JsonIgnore
    private Lekar lekar;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pregled")
    private Sala sala;

    public Pregled() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipPregleda() {
        return tipPregleda;
    }

    public void setTipPregleda(String tipPregleda) {
        this.tipPregleda = tipPregleda;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public Integer getPopust() {
        return popust;
    }

    public void setPopust(Integer popust) {
        this.popust = popust;
    }

    public String getIzvestaj() {
        return izvestaj;
    }

    public void setIzvestaj(String izvestaj) {
        this.izvestaj = izvestaj;
    }

    public Date getDatumPregleda() {
        return datumPregleda;
    }

    public void setDatumPregleda(Date datumPregleda) {
        this.datumPregleda = datumPregleda;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        return "Pregled{" +
                "id=" + id +
                ", tipPregleda='" + tipPregleda + '\'' +
                ", cena=" + cena +
                ", popust=" + popust +
                ", izvestaj='" + izvestaj + '\'' +
                ", datumPregleda=" + datumPregleda +
                ", datumKreiranja=" + datumKreiranja +
                ", pacijent=" + pacijent +
                ", lekar=" + lekar +
                ", sala=" + sala +
                '}';
    }

    @PrePersist
    protected void onCreate(){
        this.datumKreiranja = new Date();
    }
}
