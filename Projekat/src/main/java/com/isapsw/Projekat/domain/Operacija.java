package com.isapsw.Projekat.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Operacija extends Pregled_Operacija {
    @NotBlank
    private String tipOperacije;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "prisutni_lekari",
            joinColumns = @JoinColumn(name = "operacija_id"),
            inverseJoinColumns = @JoinColumn(name = "lekar_id"))
    List<Lekar> lekari = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pacijent_id", updatable = false, nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Pacijent pacijent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="medicinskaSestra_id", updatable = false, nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MedicinskaSestra medicinskaSestra;

    public Operacija() {
        super();
        setStanje(0);
        setVrsta(1);
    }

    public String getTipOperacije() {
        return tipOperacije;
    }

    public void setTipOperacije(String tipOperacije) {
        this.tipOperacije = tipOperacije;
    }

    public List<Lekar> getLekari() {
        return lekari;
    }

    public void setLekari(List<Lekar> lekari) {
        this.lekari = lekari;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public MedicinskaSestra getMedicinskaSestra() {
        return medicinskaSestra;
    }

    public void setMedicinskaSestra(MedicinskaSestra medicinskaSestra) {
        this.medicinskaSestra = medicinskaSestra;
    }

    @Override
    public String toString() {
        return "Operacija{" +
                "tipOperacije='" + tipOperacije + '\'' +
                ", lekari=" + lekari +
                '}';
    }
}
