package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Recept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean overen = false;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = false)
    private Date datumIzdavanja;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date datumIsticanja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="zdrKarton_id", updatable = false, nullable = false)
    @JsonIgnore
    private ZdrKarton zdrKarton;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="medicinskaSestra_id", updatable = false, nullable = false)
    @JsonIgnore
    private MedicinskaSestra medicinskaSestra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="lek_id", updatable = false, nullable = false)
    private Lek lek;

    @PrePersist
    protected void onCreate(){
        this.datumIzdavanja = new Date();
    }

    public Recept() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lek getLek() {
        return lek;
    }

    public void setLek(Lek lek) {
        this.lek = lek;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Date getDatumIsticanja() {
        return datumIsticanja;
    }

    public void setDatumIsticanja(Date datumIsticanja) {
        this.datumIsticanja = datumIsticanja;
    }

    public ZdrKarton getZdrKarton() {
        return zdrKarton;
    }

    public void setZdrKarton(ZdrKarton zdrKarton) {
        this.zdrKarton = zdrKarton;
    }

    public boolean isOveren() {
        return overen;
    }

    public void setOveren(boolean overen) {
        this.overen = overen;
    }

    public MedicinskaSestra getMedicinskaSestra() {
        return medicinskaSestra;
    }

    public void setMedicinskaSestra(MedicinskaSestra medicinskaSestra) {
        this.medicinskaSestra = medicinskaSestra;
    }
}
