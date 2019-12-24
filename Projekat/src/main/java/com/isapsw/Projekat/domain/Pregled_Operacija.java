package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class Pregled_Operacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tip_pregleda_id", updatable = false, nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TipPregleda tipPregleda;

    private String izvestaj;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Belgrade")
    private Date datumPocetka;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Belgrade")
    private Date datumZavrsetka;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = false)
    private Date datumKreiranja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sala_id", updatable = false, nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sala sala;

    public Pregled_Operacija() {
    }

    public Sala getSala() {
        return sala;
    }

    public TipPregleda getTipPregleda() {
        return tipPregleda;
    }

    public void setTipPregleda(TipPregleda tipPregleda) {
        this.tipPregleda = tipPregleda;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIzvestaj() {
        return izvestaj;
    }

    public void setIzvestaj(String izvestaj) {
        this.izvestaj = izvestaj;
    }

    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public Date getDatumZavrsetka() {
        return datumZavrsetka;
    }

    public void setDatumZavrsetka(Date datumZavrsetka) {
        this.datumZavrsetka = datumZavrsetka;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    @PrePersist
    protected void onCreate(){
        this.datumKreiranja = new Date();
    }
}
