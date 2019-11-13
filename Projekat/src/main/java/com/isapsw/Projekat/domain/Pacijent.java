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
public class Pacijent extends Pacijent_Zahtev{
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pacijent")
    private ZdrKarton zdrKarton;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pacijent")
    private List<Pregled> pregledi = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pacijent")
    private List<Operacija> operacije = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="adminKlinCentra_id", updatable = false, nullable = false)
    @JsonIgnore
    private AdminKlinCentra adminKlinCentra;

    public Pacijent() {
        super();
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

}
