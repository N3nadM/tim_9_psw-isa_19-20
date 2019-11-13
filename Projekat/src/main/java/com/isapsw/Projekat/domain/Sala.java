package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Broj sale + naziv klinike -- kasnije dodati naziv klinike
    @Column(unique = true, nullable = false)
    private String salaIdentifier;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pregled_id",nullable = false)
    @JsonIgnore
    private Pregled pregled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="klinika_id", updatable = false, nullable = false)
    @JsonIgnore
    private Klinika klinika;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    private Date datumKreiranja;

    public Sala() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalaIdentifier() {
        return salaIdentifier;
    }

    public void setSalaIdentifier(String salaIdentifier) {
        this.salaIdentifier = salaIdentifier;
    }

    public Pregled getPregled() {
        return pregled;
    }

    public void setPregled(Pregled pregled) {
        this.pregled = pregled;
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "id=" + id +
                ", salaIdentifier='" + salaIdentifier + '\'' +
                ", pregled=" + pregled +
                ", klinika=" + klinika +
                ", datumKreiranja=" + datumKreiranja +
                '}';
    }

    @PrePersist
    protected void onCreate(){
        this.datumKreiranja = new Date();
    }
}