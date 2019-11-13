package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@MappedSuperclass
public class Pregled_Operacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private Integer cena;

    private String izvestaj;

    @JsonFormat(pattern = "dd-M-yyyy hh:mm")
    private Date datum;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    private Date datumKreiranja;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pacijent_id", updatable = false, nullable = false)
    @JsonIgnore
    private Pacijent pacijent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="sala_id", updatable = false, nullable = false)
    @JsonIgnore
    private Sala sala;

    public Pregled_Operacija() {
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

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
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

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
